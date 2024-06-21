import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.sql.SparkSession
import org.jblas.DoubleMatrix

// 基于评分数据的LFM 隐语义模型
case class movieRating(uid:Int,mid:Int,score:Double,timestamp:Long)

case class MySQLConfig(url: String, user: String, password: String)

// 定义一个基准推荐对象
case class Recommendation(mid:Int,score:Double)

// 定义基于预测评分的用户推荐列表
case class UserRecs(uid:Int,recs: Seq[Recommendation])

// 定义基于LFM电影特征向量的电影相似度列表
case class MovieRecs(mid:Int,recs: Seq[Recommendation])
object OfflineRecommender {

  // 定义表名
  val MOVIE_TABLE = "movie"
  val RATING_SMALL_TABLE = "rating_small"
  val RATING_TABLE = "rating"
  val USER_RECS = "user_recs"
  val MOVIE_RECS = "movie_recs"

  // 最大推荐数量
  val USER_MAX_RECOMMENDATION = 20

  def main(args: Array[String]):Unit = {
    // 配置信息
    val config = Map(
      "spark.cores" -> "local[*]",
      "mysql.url" -> "jdbc:mysql://localhost:3306/recommender",
      "mysql.user" -> "root",
      "mysql.password" -> "GJyg6841!",
      "mysql.db" -> "recommender"
    )

    // 加载 MySQL 的 JDBC 驱动程序类
    Class.forName("com.mysql.cj.jdbc.Driver")

    val sparkConf = new SparkConf().setMaster(config("spark.cores"))
      .setAppName("OfflineRecommender")
      .set("spark.executor.memory", "8g")
      .set("spark.driver.memory", "4g")
      .set("spark.network.timeout", "800s")
      .set("spark.executor.heartbeatInterval", "200s")
      .set("spark.default.parallelism", "200")
      .set("spark.sql.shuffle.partitions", "200")
      .set("spark.executor.cores", "4")
      .set("spark.speculation", "true") // Enable speculative execution
      .set("spark.sql.broadcastTimeout", "36000") // Broadcast timeout for large tables

    // 创建一个sparkSession
    val spark = SparkSession.builder()
      .config(sparkConf)
      .getOrCreate()

    // 隐式转换
    import spark.implicits._

    // 隐式定义MySQL配置文件
    implicit val mysqlConfig = MySQLConfig(
      config("mysql.url"),
      config("mysql.user"),
      config("mysql.password")
    )

    // 加载数据
    // 加载rating表的数据
    val ratingRDD = spark.read
      .format("jdbc")
      .option("url", mysqlConfig.url)
      .option("dbtable", RATING_SMALL_TABLE) // 后续要换成RATING_TABLE
//      .option("dbtable", RATING_TABLE) // 后续要换成RATING_TABLE
      .option("user", mysqlConfig.user)
      .option("password", mysqlConfig.password)
      .load()
      .rdd
      .map(rating => (
        rating.getAs[Int]("uid"),
        rating.getAs[Int]("mid"),
        rating.getAs[Double]("score")
      )) // 转换成RDD，并去掉时间戳
      .repartition(100)  // 增加分区数
      .cache()

    // 构造用户、电影矩阵，从rating表中获取所有的uid、mid，并去重
    val userRDD = ratingRDD.map(_._1).distinct()
    val movieRDD = ratingRDD.map(_._2).distinct()

    // 加载隐语义模型，并准备训练集
    val trainData = ratingRDD.map(x => Rating(x._1, x._2, x._3))

    // 模型参数
    val (rank, iterations, lambda) = (20, 5, 0.1)
//    val (rank, iterations, lambda) = (5, 1, 0.01)
    val model = ALS.train(trainData, rank, iterations, lambda)

    val modelPath = "E:\\IDEAProjects\\MovieRecommendSystem\\recommender\\OfflineRecommender"
    model.save(spark.sparkContext, modelPath)
    // 基于用户和电影的隐特征，计算预测评分，得到用户的推荐列表
    // 计算user和movie的笛卡尔积，得到一个空的评分矩阵
    val userMovies = userRDD.cartesian(movieRDD)

    // 调用model的predict方法预测评分
//    val preRatings = model.predict(userMovies)

    // 调用model的predict方法预测评分，并持久化
    val preRatings = model.predict(userMovies).cache()


    //    val userRecs = preRatings
//      .filter(_.rating > 0)
//      .map( rating => (rating.user,(rating.product,rating.rating)))
//      .groupByKey()
//      .map{
//        case (uid,recs) => UserRecs(uid,recs.toList.sortWith(_._2>_._2).take(USER_MAX_RECOMMENDATION)
//        .map(x=>Recommendation(x._1,x._2)))
//      } // 这里写入MySQL时，要调整一下数据结构uid,mid:mid1|mid2|mid3...
//      .toDF()

// 将推荐结果转换成适合 MySQL 的格式
    val userRecs = preRatings
      .filter(_.rating > 0)
      .map(rating => (rating.user, (rating.product, rating.rating)))
      .groupByKey()
      .flatMap {
        case (uid, recs) =>
          val sortedRecs = recs.toList.sortWith(_._2 > _._2).take(USER_MAX_RECOMMENDATION)
          sortedRecs.map {
            case (mid, score) => (uid, mid, score)
          }
      }.toDF("uid", "mid", "rating")

    userRecs.write
      .option("url", mysqlConfig.url)
      .option("dbtable", USER_RECS)
      .option("user", mysqlConfig.user)
      .option("password", mysqlConfig.password)
      .mode("overwrite")
      .format("jdbc")
      .save()

    // 基于电影隐特征，计算相似度矩阵，得到电影的相似度列表
    val movieFeatures = model.productFeatures.map{
      case (mid,features) => (mid,new DoubleMatrix(features))
    }

    // 对所有电影，两两计算相似度，先做笛卡尔积
//    val movieRecs = movieFeatures.cartesian(movieFeatures)
//      .filter{
//        // 过滤掉自己的mid
//        case (a,b) => a._1!=b._1
//      }
//      .map{
//        case (a,b) => {
//          val simScore = this.consinSim(a._2,b._2)
//          ( a._1, (b._1,simScore))
//        }
//      }
//      .filter(_._2._2 >= 0.6)
//      .groupByKey()
//      .map{
//        case (mid,items) => MovieRecs(mid,items.toList.map(x => Recommendation(x._1,x._2)))
//      }
//      .toDF()
      val movieRecs = movieFeatures.cartesian(movieFeatures)
        .filter { case (a, b) => a._1 != b._1 }
        .map { case (a, b) =>
          val simScore = this.consinSim(a._2, b._2)
          (a._1, b._1, simScore)
        }
        .filter(_._3 >= 0.8)
        .toDF("mid1", "mid2", "simScore")

    // 将电影相似度写入MySQL
    movieRecs.write
      .option("url", mysqlConfig.url)
      .option("dbtable", MOVIE_RECS)
      .option("user", mysqlConfig.user)
      .option("password", mysqlConfig.password)
      .mode("overwrite")
      .format("jdbc")
      .save()

    spark.stop()
  }

  // 计算两个电影之间的余弦相似度
  def consinSim(movie1: DoubleMatrix, movie2: DoubleMatrix): Double = {
    movie1.dot(movie2) / (movie1.norm2() * movie2.norm2())
  }
}

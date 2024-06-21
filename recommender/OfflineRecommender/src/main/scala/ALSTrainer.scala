import breeze.numerics.sqrt
import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object ALSTrainer {

  val MOVIE_TABLE = "movie"
  val RATING_SMALL_TABLE = "rating_small"
  val RATING_TABLE = "rating"
  val USER_RECS = "user_recs"
  val MOVIE_RECS = "movie_recs"

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
      .setAppName("ALSTrainer")
      .set("spark.executor.memory", "8g")
      .set("spark.driver.memory", "4g")
      .set("spark.executor.cores", "4")
      .set("spark.default.parallelism", "8")

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
      .as[movieRating]
      .rdd
      .map {
        rating => Rating(rating.uid,rating.mid,rating.score)
      }
      .repartition(100) // 增加分区数
      .cache()

    // 随机切分数据集，生成训练集和测试集
    val splits = ratingRDD.randomSplit(Array(0.8,0.2))
    val trainRDD = splits(0)
    val testRDD = splits(1)

    // 模型参数选择，输出最优参数
    adjustALSParam(trainRDD,testRDD)

    spark.close()
  }

  def adjustALSParam(trainData: RDD[Rating], testData: RDD[Rating]) :Unit = {
    val result = for( rank <- Array(5,10,20); lambda <- Array( 0.001, 0.01 , 0.1) )
      yield {
        val model = ALS.train(trainData,rank,5,lambda)
        val rmse = getRMSE( model, testData)
        ( rank,lambda,rmse )
      }

    // 控制台打印输出最优参数
    println(result.minBy(_._3))
  }

  def getRMSE(model: MatrixFactorizationModel, data: RDD[Rating]): Double = {
    // 计算预测评分
    val userProducts = data.map(item => (item.user, item.product))
    val predictRating = model.predict(userProducts)

    // 以uid， mid作为外键，inner join实际值与预测值
    val observed = data.map(item => ((item.user, item.product), item.rating))
    val predict = predictRating.map(item => ((item.user, item.product), item.rating))

    // 内连接得到(uid,mid),(actual,predict)
    sqrt (
      observed.join(predict).map {
        case ((uid, mid), (actual, pre)) =>
          val err = actual - pre
          err * err
      }.mean()
    )
  }
}

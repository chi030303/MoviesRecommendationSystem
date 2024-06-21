package org.streaming
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import java.util.Properties

// 连接助手对象
object ConnHelper extends Serializable {
  // 加载驱动
  Class.forName("com.mysql.cj.jdbc.Driver")

  // MySQL 数据库连接信息
  val url = "jdbc:mysql://localhost:3306/recommender"
  val username = "root"
  val password = "GJyg6841！"

  // Redis 连接信息
  lazy val jedis: Jedis = new Jedis("localhost",6379)
  // 使用 lazy 来延迟初始化
  lazy val connection: Connection = {
    val props = new Properties()
    props.setProperty("user", username)
    props.setProperty("password", password)
    DriverManager.getConnection(url, props)
  }
}

//case class MySQLConfig(url: String, user: String, password: String, db: String)
case class MySQLConfig(url: String, user: String, password: String)

// 定义一个基准推荐对象
case class Recommendation(mid:Int, score:Double)

// 定义基于预测评分的用户推荐列表
case class UserRecs(uid:Int, recs: Seq[Recommendation])

// 定义基于LFM电影特征向量的电影相似度列表
case class MovieRecs(mid:Int, recs: Seq[Recommendation])

object StreamingRecommender {

  val MAX_USER_RATINGS_NUM = 20
  val MAX_SIM_MOVIE_NUM = 20
  // 根据评分表来过滤掉用户已经看过的电影
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
      "mysql.db" -> "recommender",
      "kafka.topic" -> "recommender"
    )

    // 加载 MySQL 的 JDBC 驱动程序类
    Class.forName("com.mysql.cj.jdbc.Driver")

    val sparkConf = new SparkConf().setMaster(config("spark.cores"))
      .setAppName("OnlineRecommender")

    // 创建一个sparkSession
    val spark = SparkSession.builder()
      .config(sparkConf)
      .getOrCreate()

    // 拿到streaming context
    val sc = spark.sparkContext
    val ssc = new StreamingContext(sc,Seconds(2)) // batch duraion

    // 隐式转换

    import spark.implicits._

    // 隐式定义MySQL配置文件
    implicit val mysqlConfig = MySQLConfig(
      config("mysql.url"),
      config("mysql.user"),
      config("mysql.password")
    )

    // 加载电影相似度矩阵数据，广播出去
//    val simMovieMatrix = spark.read
//      .format("jdbc")
//      .option("url", mysqlConfig.url)
//      .option("dbtable", MOVIE_RECS) // 后续要换成RATING_TABLE
//      .option("user", mysqlConfig.user)
//      .option("password", mysqlConfig.password)
//      .load()
//      .as[MovieRecs]
//      .rdd
//      .map{
//        movieRecs => // 为了保证查询相似度的速度，将数据结构转换成Map
//          (movieRecs.mid, movieRecs.recs.map(
//              x => (x.mid,x.score)
//            ).toMap
//          )
//      }.collectAsMap()
    val simMovieMatrix: Map[Int, Map[Int, Double]] = processMovieRecsData(spark)

    val simMovieMatrixBroadCast = sc.broadcast(simMovieMatrix)

    // 定义kafka连接参数
    val kafkaParam = Map(
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "recommender",
      "auto.offset.reset" -> "latest"
    )

    // 通过kafka创建一个DStream
    val kafkaStream = KafkaUtils.createDirectStream[String,String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String]( Array(config("kafka.topic")),kafkaParam )
    )

    /** 把原始数据转换成评分流
     *
     * 原始数据：UID|MID|SCORE|TIMESTAMP
     */
    val ratingStream = kafkaStream.map{
      msg =>
        // 使用|分隔数据
        val attr = msg.value().split("\\|")
        ( attr(0).toInt,attr(1).toInt,attr(2).toDouble,attr(3).toInt )
    }

    // 继续做流式处理
    ratingStream.foreachRDD{
      rdds => rdds.foreach{
        case(uid,mid,score,timestamp) =>
          println("rating data coming! >>>>>>>>>>>")

          // 1. 从redis里获取当前用户最近的K次评分，保存成Array[(mid,score)]
          val userRecentlyRating  = getUserRecentlyRating( MAX_USER_RATINGS_NUM, uid, ConnHelper.jedis)

          // 2. 从相似度矩阵中，取出当前电影最相似的N个电影，作为备选列表，Array[mid]
          val candidateMovies = getTopSimMovies( MAX_SIM_MOVIE_NUM, mid, uid, simMovieMatrixBroadCast.value)

          // 3. 对每个备选电影，计算推荐优先级，得到当前用户的实时推荐列表，Array[(mid,score)]
          val streamRecs = computeMovieScores( candidateMovies, userRecentlyRating, simMovieMatrixBroadCast.value)

          // 4. 把推荐数据保存到MySQL中
          saveDataToMySQL(uid,streamRecs)
      }
    }

    // 开始接收和处理数据
    ssc.start()

    // 直到异常或退出才停止
    println(">>>>>>>>>> streaming started!")
    ssc.awaitTermination()
  }

  // 读取并处理电影推荐列表数据
  def processMovieRecsData(spark: SparkSession)(implicit mySQLConfig: MySQLConfig): Map[Int, Map[Int, Double]] = {
    // 隐式转换
    import spark.implicits._

    // 从 MySQL 加载电影推荐列表数据
    val movieRecsDF: DataFrame = spark.read
      .format("jdbc")
      .option("url", mySQLConfig.url)
      .option("dbtable", MOVIE_RECS)
      .option("user", mySQLConfig.user)
      .option("password", mySQLConfig.password)
      .load()

    // 转换为 MovieRecs 类型，并将数据结构转换为 Map
    val movieRecsMap: Map[Int, Map[Int, Double]] = movieRecsDF
      .as[(Int, Int, Double)] // 假设电影推荐列表的数据结构是 (mid1: Int, mid2: Int, simScore: Double)
      .rdd
      .map {
        case (mid1, mid2, simScore) => (mid1, (mid2, simScore))
      }
      .groupByKey()
      .mapValues(_.map { case (mid2, simScore) => (mid2, simScore) }.toMap)
      .collect()
      .toMap

    movieRecsMap
  }

  // 为了Redis操作返回的List对象和map进行类型转换
  import scala.collection.JavaConversions._

  /**
   * 获取当前最近的 M 次电影评分
   * @param num 评分个数
   * @param uid 评分用户
   * @param jedis
   * @return
   */
  def getUserRecentlyRating(num: Int, uid: Int, jedis: Jedis): Array[(Int,Double)] = {
    // 从Redis读取数据，用户评分数据保存在 uid:UID 为Key的队列里，value是 MID:SCORE
    jedis.lrange("uid:" + uid, 0, num-1)
      .map{
        item => // 具体每个评分是以:分隔的两个值
          val attr = item.split("\\:")
          ( attr(0).trim.toInt, attr(1).trim.toDouble )
      }
      .toArray
  }

  /**
   * 获取跟当前电影最相似的num个电影，作为备选电影
   * @param num 相似电影的数量
   * @param mid 当前电影的 ID
   * @param uid        当前的评分用户
   * @param simMovies  电影相似度矩阵的广播变量值
   * @param mysqlConfig MySQL 的配置
   * @return
   */
  def getTopSimMovies(num: Int, mid: Int, uid: Int,
                      simMovies: scala.collection.Map[Int, scala.collection.immutable.Map[Int, Double]])
                     (implicit mySQLConfig: MySQLConfig)
  : Array[Int] = {
    // 1. 从相似度矩阵中拿到所有相似的电影
    val allSimMovies = simMovies(mid).toArray

    // 2. 从MySQL中查询用户已经看过的电影
    val conn = ConnHelper.connection
    val statement = conn.prepareStatement("SELECT movieId FROM rating WHERE userId = ?")
    statement.setInt(1, uid)
    val rs: ResultSet = statement.executeQuery()

    val watchedMovies = scala.collection.mutable.Set[Int]()
    while (rs.next()) {
      watchedMovies.add(rs.getInt("mid"))
    }
    rs.close()
    statement.close()

    // 3. 过滤掉用户已经看过的电影，并排序输出
    allSimMovies.filterNot { case (movieId, _) => watchedMovies.contains(movieId) }
      .sortBy(-_._2)
      .take(num)
      .map(_._1)
  }

  def computeMovieScores(candidateMovies: Array[Int], userRecentlyRatings: Array[(Int,Double)],
                         simMovies: scala.collection.Map[Int, scala.collection.immutable.Map[Int, Double]])
  : Array[(Int,Double)] = {
    // 定义一个ArrayBuffer， 用于保存每一个备选电影的基础得分
    val scores = scala.collection.mutable.ArrayBuffer[(Int,Double)]()

    // 定义一个HashMap，保存一个备选电影的增强减弱因子
    val increMap = scala.collection.mutable.HashMap[Int,Int]()
    val decreMap = scala.collection.mutable.HashMap[Int,Int]()

    for ( candidateMovie <- candidateMovies; userRecentlyRating <- userRecentlyRatings){
      // 拿到备选电影和最近评分电影的相似度
      val simScore = getMoviesSimScore( candidateMovie, userRecentlyRating._1, simMovies )

      if(simScore > 0.85){
        // 计算备选电影的基础推荐得分
        scores += ( (candidateMovie,simScore * userRecentlyRating._2) )
        if (userRecentlyRating._2 > 3){
          increMap(candidateMovie) = increMap.getOrDefault(candidateMovie, 0) + 1
        } else {
          decreMap(candidateMovie) = decreMap.getOrDefault(candidateMovie, 0) + 1
        }
      }
    }

    // 根据备选电影的mid做groupby，根据公式去求最后的推荐评分
    scores.groupBy(_._1).map {
      // scoreList 是groupBy之后得到的数据， Map(mid -> ArrayBuffer[(mid,score)])
      case (mid, scoreList) =>
        (mid, scoreList.map(_._2).sum / scoreList.length + log(increMap.getOrDefault(mid, 1)
          - decreMap.getOrDefault(mid, 1)))
    }.toArray
  }

  // 获取两个电影的相似度
  def getMoviesSimScore(mid1: Int, mid2: Int,
                        simMovies: scala.collection.Map[Int, scala.collection.immutable.Map[Int, Double]])
  :Double = {
    // 处理查找不到的情况
    simMovies.get(mid1) match {
      case Some(sims) => sims.get(mid2) match {
        case Some(score) => score
        case None => 0.0
      }
      case None => 0.0
    }
  }

  // 求对数，底数默认为10
  def log(m:Int):Double={
    val N = 10
    math.log(m) / math.log(N)
  }

  // 将实时推荐数据写入MySQL数据库
  def saveDataToMySQL(uid: Int, streamRecs: Array[(Int,Double)])(implicit mySQLConfig: MySQLConfig): Unit = {
    // 定义到MySQL的连接
    val connection: Connection = DriverManager.getConnection(mySQLConfig.url, mySQLConfig.user, mySQLConfig.password)
    try {
      // 先删除已有的uid对应的数据
      val deleteSQL = "DELETE FROM stream_recs WHERE uid = ?"
      val deleteStatement: PreparedStatement = connection.prepareStatement(deleteSQL)
      deleteStatement.setInt(1, uid)
      deleteStatement.executeUpdate()
      deleteStatement.close()

      // 插入新的streamRecs数据
      val insertSQL = "INSERT INTO stream_recs (uid, mid, score) VALUES (?, ?, ?)"
      val insertStatement: PreparedStatement = connection.prepareStatement(insertSQL)
      streamRecs.foreach { case (mid, score) =>
        insertStatement.setInt(1, uid)
        insertStatement.setInt(2, mid)
        insertStatement.setDouble(3, score)
        insertStatement.addBatch()
      }
      insertStatement.executeBatch()
      insertStatement.close()
    } finally {
      connection.close()
    }
  }
}

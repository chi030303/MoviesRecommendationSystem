package org.movies
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient

import java.net.InetAddress
import java.util.Properties


/**
 * Project:MovieRecommendationSystem
 * Package:com.qige.MRS
 * Version:1.0
 *
 * Created by he kiki on 2024/05/07 14:50
 */

/** Movie 数据集
 * 100  电影ID,mid
 * "Lock, Stock and Two Smoking Barrels"  电影译名,title
 * "Lock, Stock and Two Smoking Barrels"  电影原名,ori_title
 * Comedy|Crime 电影类型,genres
 * en 电影制作语言，ori_language
 * English  电影放映语言,spoken_language
 * A card sharp and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door. 电影简介,desc
 * 105.0  电影时长,runtime
 * 1998-03-05 电影发布日期,release_date
 * Handmade Films Ltd.|Summit Entertainment|PolyGram Filmed Entertainment|SKA Films|The Steve Tisch Company   电影制作公司,production_companies
 * United Kingdom 电影制作国家,production_countries
 * Released 状态,status
 * 1350000  电影经费,budget
 * 3897569.0  电影收益,revenue
 * 4.60786  电影流行度,popularity
 * 1671.0 评分人数,vote_count
 * 7.5  评分，vote_average
 * John Lasseter  导演,director
 * Tom Hanks|Tim Allen|Don Rickles|Jim Varney|Wallace Shawn|John Ratzenberger|Annie Potts|John Morris|Erik von Detten|Laurie Metcalf|R. Lee Ermey|Sarah Freeman|Penn Jillette 演员,actor
 * Woody (voice)|Buzz Lightyear (voice)|Mr. Potato Head (voice)|Slinky Dog (voice)|Rex (voice)|Hamm (voice)|Bo Peep (voice)|Andy (voice)|Sid (voice)|Mrs. Davis (voice)|Sergeant (voice)|Hannah (voice)|TV Announcer (voice)  饰演角色,character
 */

/**
 * @param mid
 * @param title
 * @param original_title
 * @param genres
 * @param original_language
 * @param spoken_language
 * @param overview
 * @param runtime
 * @param release_date
 * @param production_companies
 * @param production_countries
 * @param status
 * @param budget
 * @param revenue
 * @param popularity
 * @param vote_count
 * @param vote_average
 * @param director
 * @param actor
 * @param character
 */
case class Movie(mid:Int,title:String,original_title:String,genres:String,original_language:String,spoken_language:String,
                 overview:String,runtime:Int,release_date:String,production_companies:String,
                 production_countries:String,status:String,budget:Int,revenue:Int,popularity:Double,vote_count:Int,
                 vote_average:Double,director:String,actor:String,character:String)

/**
 * 1 用户id,uid
 * 31 电影id,mid
 * 2.5 评分,score
 * 1260759144 评分时间,timestamp
 */

/**
 *
 * @param uid
 * @param mid
 * @param score
 * @param timestamp
 */
case class Rating(uid:Int,mid:Int,score:Double,timestamp:Long)

/**
 * 862 电影id,mid
 * 931 用户id,uid
 * jealousy 关键词,tag
 */

/**
 *
 * @param uid
 * @param mid
 * @param tag
 */
case class Keyword(uid:Int,mid:Int,tag:String)

// 把MySQL 和 ES 的配置封装成样例类

/**
 *
 * @param url 数据库连接地址
 * @param user  数据库用户名
 * @param password  数据库用户密码
 */
// 定义 MySQL 的连接信息的 case class
case class MySQLConfig(url: String, user: String, password: String)

/**
 *
 * @param httpHosts       http主机列表，逗号分隔
 * @param transportHosts  transport主机列表
 * @param index           需要操作的索引名称
 * @param clustername     集群名称，默认elasticsearch
 */
case class ESConfig(httpHosts:String,transportHosts:String,index:String,clustername:String)

/**
 *
 * @param id 电影ID
 * @param poster_path 电影海报URL
 * @param title 电影中文标题
 * @param genres  电影类别
 */
case class ExtraData(id: Int, poster_path: String, title: String, genres: String)
object DataLoader {

  // 定义常量
  val MOVIE_DATA_PATH = "E:\\IDEAProjects\\MovieRecommendSystem\\recommender\\DataLoader\\src\\main\\resources\\movies.csv"
  val RATING_SMALL_DATA_PATH = "E:\\IDEAProjects\\MovieRecommendSystem\\recommender\\DataLoader\\src\\main\\resources\\ratings_small.csv"
  val KEYWORD_DATA_PATH = "E:\\IDEAProjects\\MovieRecommendSystem\\recommender\\DataLoader\\src\\main\\resources\\keywords.csv"
  val EXTRA_DATA_PATH = "E:\\IDEAProjects\\MovieRecommendSystem\\recommender\\DataLoader\\src\\main\\resources\\extra_data.csv"
  val RATING_DATA_PATH = "E:\\IDEAProjects\\MovieRecommendSystem\\recommender\\DataLoader\\src\\main\\resources\\ratings.csv"

//  val MONGODB_MOVIE_COLLECTION = "movie"
//  val MONGODB_RATING_COLLECTION = "rating"
//  val MONGODB_KEYWORD_COLLECTION = "keyword"
  val ES_MOVIE_INDEX = "movie"

  def main(args: Array[String]): Unit = {

    // 配置信息
    val config = Map(
      "spark.cores" -> "local[*]",
      "mysql.url" -> "jdbc:mysql://localhost:3306/recommender",
      "mysql.user" -> "root",
      "mysql.password" -> "GJyg6841!",
      "mysql.db" -> "recommender",
      "es.httpHosts" -> "localhost:9200",
      "es.transportHosts" -> "localhost:9300",
      "es.index" -> "recommender",
      "es.cluster.name" -> "elasticsearch"
    )

    // 加载 MySQL 的 JDBC 驱动程序类
    Class.forName("com.mysql.cj.jdbc.Driver")

    // 创建一个SparkConfig对象
    val sparkConf = new SparkConf().setMaster(config("spark.cores")).setAppName("DataLoader")

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

//    // 从 MOVIE_DATA_PATH 中加载 RDD 数据
//    val movieRDD = spark.sparkContext.textFile(MOVIE_DATA_PATH)
//
//    // 将 MovieRDD 转换为 DataFrame
//    val movieDF = movieRDD.map { item =>
//      val attr = item.split("\\^")
//      Movie(attr(0).toInt, attr(1).trim, attr(2).trim, attr(3).trim, attr(4).trim,
//        attr(5).trim, attr(6).trim, attr(7).trim.toInt, attr(8).trim, attr(9).trim,
//        attr(10).trim, attr(11).trim, attr(12).trim.toInt, attr(13).trim.toInt,
//        attr(14).trim.toDouble, attr(15).trim.toInt, attr(16).trim.toDouble,
//        attr(17).trim, attr(18).trim, attr(19).trim)
//    }.toDF()
//
//    // 从 KEYWORD_DATA_PATH 中加载 RDD 数据
//    val keywordRDD = spark.sparkContext.textFile(KEYWORD_DATA_PATH)
//
//    // 将 KeywordRDD 转换为 DataFrame
//    val keywordDF = keywordRDD.map { item =>
//      val attr = item.split(",")
//      Keyword(attr(0).toInt, attr(1).toInt, attr(2).trim)
//    }.toDF()
//
//    // 从 EXTRA_DATA_DATA_PATH 中加载 RDD 数据
//    val extraDataRDD = spark.sparkContext.textFile(EXTRA_DATA_PATH)
//
//    // 将 ExtraDataRDD 转换为 DataFrame
//    val extraDF = extraDataRDD.map { item =>
//      val attr = item.split(",")
//      ExtraData(attr(0).toInt, attr(1).trim, attr(2).trim, attr(3).trim)
//    }.toDF()
//
//    // 从 RATING_DATA_PATH 中加载 RDD 数据
//    val ratingRDD = spark.sparkContext.textFile(RATING_DATA_PATH)
//
//    // 将 RatingRDD 转换为 DataFrame
//    val ratingDF = ratingRDD.map { item =>
//      val attr = item.split(",")
//      Rating(attr(0).toInt, attr(1).toInt, attr(2).toDouble, attr(3).toLong)
//    }.toDF()
//
//    // 从 RATING_DATA_PATH 中加载 RDD 数据
//    val rating_smallRDD = spark.sparkContext.textFile(RATING_SMALL_DATA_PATH)
//
//    // 将 RatingRDD 转换为 DataFrame
//    val rating_smallDF = ratingRDD.map { item =>
//      val attr = item.split(",")
//      Rating(attr(0).toInt, attr(1).toInt, attr(2).toDouble, attr(3).toLong)
//    }.toDF()
    // 加载 movie 数据并转换为 DataFrame
    val movieDF = spark.read.option("header", "true")
      .option("delimiter", "^")
      .csv(MOVIE_DATA_PATH)
      .withColumnRenamed("id", "mid")

    // 加载 Rating 数据并转换为 DataFrame
    val ratingDF = spark.read.option("header", "true").csv(RATING_DATA_PATH)
      .withColumnRenamed("userId", "uid")
      .withColumnRenamed("movieId", "mid")
      .withColumnRenamed("rating", "score")
      .withColumnRenamed("timestamp", "timestamp")

    // 加载 Rating_small 数据并转换为 DataFrame
    val rating_smallDF = spark.read.option("header", "true").csv(RATING_SMALL_DATA_PATH)
      .withColumnRenamed("userId", "uid")
      .withColumnRenamed("movieId", "mid")
      .withColumnRenamed("rating", "score")
      .withColumnRenamed("timestamp", "timestamp")

    // 加载 Tag 数据并转换为 DataFrame
    val keywordDF = spark.read.option("header", "true").csv(KEYWORD_DATA_PATH)
      .withColumnRenamed("userId", "uid")
      .withColumnRenamed("movieId", "mid")
      .withColumnRenamed("tag", "keyword")

    // 读取 extradata.csv 文件并将数据加载到 DataFrame 中
    val extraDF = spark.read.option("header", "true").csv(EXTRA_DATA_PATH)
      .withColumnRenamed("id", "mid")

    // 将数据写入到 MySQL 数据库
//    storeDataInMySQL(movieDF, keywordDF, ratingDF, extraDF,rating_smallDF)(mysqlConfig)

    // 数据预处理，把movie 对应的keyword信息添加进去，加一列 keyword1|keyword2|...
    import org.apache.spark.sql.functions._

    /**
     *  movieId,keywords
     *
     *  keywords:keyword1|keyword2|...
     */
    val newKeyword = keywordDF.groupBy($"mid")
      .agg(concat_ws("|",collect_set($"keyword")).as("keywords"))
      .select("mid","keywords")

    // newKeyword 和 movie做join操作，合并在一起,左外连接
    val movieWithNewkeywords = movieDF.join(newKeyword,Seq("mid"),"left")

    implicit val esConfig = ESConfig(config.get("es.httpHosts").get
      ,config.get("es.transportHosts").get
      ,config.get("es.index").get
      ,config.get("es.cluster.name").get)

    // 将数据保存到ES
    storeDataInES(movieWithNewkeywords)

    spark.stop()
  }

  //  将数据写入MySQL
  def storeDataInMySQL(movieDF:DataFrame, keywordDF: DataFrame, ratingDF: DataFrame, extraDF: DataFrame,rating_smallDF:DataFrame)(implicit mysqlConfig: MySQLConfig): Unit = {
    // 创建 MySQL 连接属性
    val connectionProperties = new Properties()
    connectionProperties.put("user", mysqlConfig.user)
    connectionProperties.put("password", mysqlConfig.password)

    try {
      // 将 DataFrame 中的数据写入到 MySQL 数据库中的相应表中
      movieDF.write
        .mode("overwrite")
        .jdbc(mysqlConfig.url, "movie", connectionProperties)

      keywordDF.write
        .mode("overwrite")
        .jdbc(mysqlConfig.url, "keyword", connectionProperties)

      ratingDF.write
        .mode("overwrite")
        .jdbc(mysqlConfig.url, "rating", connectionProperties)

      extraDF.write
        .mode("overwrite")
        .jdbc(mysqlConfig.url, "extra_data", connectionProperties)

      rating_smallDF.write
        .mode("overwrite")
        .jdbc(mysqlConfig.url, "rating_small", connectionProperties)
    } catch {
      case e: Exception =>
        // 处理异常
        e.printStackTrace()
    }
  }

  // 将数据导入ES
  def storeDataInES(movieDF: DataFrame)(implicit esConfig: ESConfig): Unit = {
    // 新建ES配置
    val settings: Settings = Settings.builder()
      .put("transport.type", "netty3") // 把netty版本统一，不然会报方法找不到的错误
      .put("http.type", "netty3")
      .put("cluster.name", esConfig.clustername)
      .build()

    // 新建ES客户端
    val esClient = new PreBuiltTransportClient(settings)

    // 将TransportHosts 添加到esClient中
    // 主机名host: 多个端口port
    val REGET_HOST_POST = "(.+):(\\d+)".r
    esConfig.transportHosts.split(",").foreach {
      // 进行模式匹配
      case REGET_HOST_POST(host: String, port: String) =>
        // 把主机号和端口号传给客户端
        esClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port.toInt))
    }

    try {
      // 先清理遗留数据
      if (esClient.admin().indices().exists(new IndicesExistsRequest(esConfig.index))
        .actionGet()
        .isExists
      ) {
        esClient.admin().indices().delete(new DeleteIndexRequest(esConfig.index))
      }

      esClient.admin().indices().create(new CreateIndexRequest(esConfig.index))
      // 定义一个日期格式化函数
      val formatDate = udf((date: String) => {
        try {
          // 尝试解析日期，如果解析失败则返回 null
          java.time.LocalDate.parse(date).toString
        } catch {
          case _: Throwable => null
        }
      }, StringType)

      // 对每个字段进行类型转换和日期格式化
      val formattedMovieDF = movieDF
        .na.fill("Unknown") // 将空值填充为 "Unknown"
        .withColumn("release_date", formatDate(col("release_date"))) // 格式化 release_date 字段为日期类型

      try {
        formattedMovieDF.write
          .option("es.nodes", esConfig.httpHosts) // 主机节点
          .option("es.http.timeout", "100m") // 超时
          .option("es.mapping.id", "mid") // 映射主键
          .option("es.nodes.wan.only", "true")
          .mode("overwrite")
          .format("org.elasticsearch.spark.sql")
          .save(esConfig.index + "/" + ES_MOVIE_INDEX)
      } catch {
        case e: Exception =>
          // 处理异常，比如记录日志
          e.printStackTrace()
      }
    } finally {
      esClient.close()
    }
  }
}


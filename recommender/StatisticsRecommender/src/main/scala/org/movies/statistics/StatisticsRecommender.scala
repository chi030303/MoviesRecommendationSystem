package org.movies.statistics

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.text.SimpleDateFormat
import java.util.Date

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
 *
 * @param url 数据库连接地址
 * @param user  数据库用户名
 * @param password  数据库用户密码
 */
// 定义 MySQL 的连接信息的 case class
case class MySQLConfig(url: String, user: String, password: String)

/**
 *
 * @param mid
 * @param score
 */
// 基准推荐对象
case class Recommendation(mid:Int,score:Double)

/**
 *
 * @param gerens
 * @param recs
 */
// 电影类别top10 推荐对象
case class GenresRecommendation(gerens:String,recs: Seq[Recommendation])
object StatisticsRecommender {

  // 定义表名
  val MOVIE_TABLE = "movie"
//  val RATING_SMALL_TABLE = "rating_small"
  val RATING_TABLE = "rating"
  val POPULAR_MOVIE_TABLE = "popular_movie"
  val POPULAR_MOVIE_RENCETLY_TABLE = "popular_movie_recently"
  val TOP_MOVIE_TABLE = "top_movie"
  val GENRES_TOP_MOVIE_TABLE = "genres_top_movie"

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

  def main(args: Array[String]): Unit = {
    // 创建一个SparkConfig对象
    val sparkConf = new SparkConf().setMaster(config("spark.cores")).setAppName("StatisticsRecommender")

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

    // 从MySQL加载数据
    // 加载movie表的数据
    val movieDF = spark.read
      .format("jdbc")
      .option("url", mysqlConfig.url)
      .option("dbtable", MOVIE_TABLE)
      .option("user", mysqlConfig.user)
      .option("password", mysqlConfig.password)
      .load()
      .toDF()

    // 加载rating表的数据
    val ratingDF = spark.read
      .format("jdbc")
      .option("url", mysqlConfig.url)
//      .option("dbtable", RATING_SMALL_TABLE) // 后续要换成RATING_TABLE
      .option("dbtable", RATING_TABLE) // 后续要换成RATING_TABLE
      .option("user", mysqlConfig.user)
      .option("password", mysqlConfig.password)
      .load()
      .toDF()

    // 显示数据（可选）

    //创建 ratings_temp、movie_temp 的临时视图
    ratingDF.createOrReplaceTempView("ratings_temp")
    movieDF.createOrReplaceTempView("movie_temp")

    // TODO:不同的统计推荐结果
    // 1. 历史热门统计，历史评分数据最多，mid,vote_count
    val popularMoviesDF = spark.sql("select mid,vote_count,popularity from movie_temp")
    storeDFInMySQL(popularMoviesDF,POPULAR_MOVIE_TABLE)

    // 2. 近期热门统计，按照"yyyyMM"格式选取最近的评分数据，统计评分个数
    // 创建一个日期格式化工具
    val simpleDateFormat = new SimpleDateFormat("yyyyMM")
    // 注册udf，把时间戳转换成年月日
    spark.udf.register("changeDate",(x: Int)=>simpleDateFormat.format(new Date(x * 1000L)).toInt)

    // 对原数据进行预处理，去掉uid
    val ratingOfYearMonth = spark.sql("select mid,score,changeDate(timestamp) as yearMonth from ratings_temp")
    ratingOfYearMonth.createOrReplaceTempView("ratingOfMonth")

    // 从ratingOfYearMonth中查找电影在各个月份的评分人数,mid,vote_count,yearmonth
    val popularMovieRecentlyDF = spark.sql(
      """
       select ratingOfMonth.mid, count(ratingOfMonth.mid) AS count, ratingOfMonth.yearmonth,
       movie_temp.popularity from ratingOfMonth join movie_temp on ratingOfMonth.mid = movie_temp.mid
       group by ratingOfMonth.yearmonth, ratingOfMonth.mid, movie_temp.popularity
       order by ratingOfMonth.yearmonth DESC, count DESC
     """.stripMargin)

    storeDFInMySQL(popularMovieRecentlyDF,POPULAR_MOVIE_RENCETLY_TABLE)
    // 3. 优质电影统计，根据平均分 vote_average
    val averageMovieDF = spark.sql("select mid, vote_average as avg from movie_temp order by avg desc")
    storeDFInMySQL(averageMovieDF,TOP_MOVIE_TABLE)

    // 4. 各类别电影Top统计
    // 定义所有类别
    // TODO:后续查询的时候，直接select mid where genre == "input" from genres_top_movie
    val genres = {
      List("Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy"
        , "Foreign", "History", "Horror", "Music", "Mystery", "Romance", "Science Fiction", "TV MOVIE", "Thriller",
        "War", "Western")
    }

    // 把平均评分 vote_average 加入
    // 为了做笛卡尔积，把genres转成RDD
    val genresRDD = spark.sparkContext.makeRDD(genres)

    // 计算类别top50,对类别和电影做笛卡尔积
    val genresTopMoviesDF = genresRDD.cartesian(movieDF.rdd)
      .filter {
        // 条件过滤，找出 movie 的字段 genres 值包含当前类别中的哪些类别
        case (genre, movieRow) =>
          movieRow.getAs[String]("genres").toLowerCase.contains(genre.toLowerCase)
      }
      .map {
        case (genre, movieRow) => (genre, (movieRow.getAs[Int]("mid"), movieRow.getAs[Double]("vote_average")))
      }
      .groupByKey()
      .map {
        // 根据第二个元素 vote_average 降序排序
        case (genre, items) =>
          val recommendations = items.toList.sortWith(_._2 > _._2).take(50)
          recommendations.map(item => (genre, item._1, item._2))
      }
      .flatMap(identity) // 展开为单独的行
      .toDF("genre", "mid", "vote_average")

      storeDFInMySQL(genresTopMoviesDF,GENRES_TOP_MOVIE_TABLE)

      spark.stop()
  }

  def storeDFInMySQL(df: DataFrame, table_name: String)(implicit mysqlConfig: MySQLConfig) :Unit ={
      df.write
        .option("url", mysqlConfig.url)
        .option("dbtable", table_name)
        .option("user", mysqlConfig.user)
        .option("password", mysqlConfig.password)
        .mode("overwrite")
        .format("jdbc")
        .save()
  }
}

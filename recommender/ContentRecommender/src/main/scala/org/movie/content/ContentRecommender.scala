package org.movie.content

import org.apache.spark.SparkConf
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
import org.apache.spark.ml.linalg.SparseVector
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.jblas.DoubleMatrix

case class Movie(mid: Int, title: String, original_title: String, genres: String, original_language: String, spoken_languages: String,
                 overview: String, runtime: Int, release_date: String, production_companies: String,
                 production_countries: String, status: String, budget: Int, revenue: Long,
                 popularity: Double, vote_count: Int, vote_average: Double, director: String, actor: String, character: String)

case class MySQLConfig(url: String, user: String, password: String, driver: String = "com.mysql.cj.jdbc.Driver")

// 定义基于电影内容信息提取出来的特征向量的电影相似度列表
case class Recommendation(mid: Int, score: Double)

case class MovieRecs(mid: Int, recs: Seq[Recommendation])

object ContentRecommender {

  val MOVIE_TABLE = "movie"
  val CONTENT_MOVIE_RECS = "content_movie_recs"

  def main(args: Array[String]): Unit = {
    val config = Map(
      "spark.cores" -> "local[*]",
      "mysql.url" -> "jdbc:mysql://localhost:3306/recommender",
      "mysql.user" -> "root",
      "mysql.password" -> "GJyg6841!",
      "mysql.db" -> "recommender"
    )

    Class.forName("com.mysql.cj.jdbc.Driver")

    val sparkConf = new SparkConf().setMaster(config("spark.cores"))
      .setAppName("ContentRecommender")

    val spark = SparkSession.builder()
      .config(sparkConf)
      .getOrCreate()

    // 调整partition数量
    val numPartitions = 100

    import spark.implicits._

    implicit val mysqlConfig = MySQLConfig(
      config("mysql.url"),
      config("mysql.user"),
      config("mysql.password")
    )

    val movieTagsDF = spark.read.format("jdbc")
      .option("url", mysqlConfig.url)
      .option("driver", mysqlConfig.driver)
      .option("dbtable", MOVIE_TABLE)
      .option("user", mysqlConfig.user)
      .option("password", mysqlConfig.password)
      .load()
      .select("mid", "title", "genres")
      .repartition(numPartitions) // 调整分区数量
      .map(x => (x.getAs[Int]("mid"), x.getAs[String]("title"), x.getAs[String]("genres").replace('|', ' ')))
      .toDF("mid", "title", "genres")
      .cache()

    movieTagsDF.show()

    // 使用TF-IDF从内容信息中提取电影特征向量
    // 创建一个分词器，默认按空格分词
    val tokenizer = new Tokenizer().setInputCol("genres").setOutputCol("words")
    // 用分词器对原始数据做转换，生成新的一列words
    val wordData = tokenizer.transform(movieTagsDF)

    val hashingTF = new HashingTF().setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(1000)
    val featurizeData = hashingTF.transform(wordData)

    // 引入IDF工具，可以得到idf模型
    val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
    // 训练idf模型，得到每个词的逆文档概率
    val idfModel = idf.fit(featurizeData)

    // 用模型对原数据进行处理，得到文档中每个词
    val rescaleData = idfModel.transform(featurizeData)

    rescaleData.show()

    val movieFeatures = rescaleData.map(
      row => (row.getAs[Int]("mid"), row.getAs[SparseVector]("features").toArray)
    ).rdd
      .map(x => (x._1, new DoubleMatrix(x._2)))

    movieFeatures.cache()

    val movieFeatureBroadcast: Broadcast[Array[(Int, DoubleMatrix)]] = spark.sparkContext.broadcast(movieFeatures.collect())

    val movieRecs = movieFeatures.flatMap { case (mid, features) =>
      movieFeatureBroadcast.value.map { case (mid2, features2) =>
        val simScore = if (mid != mid2) this.consinSim(features, features2) else 0.0
        (mid, mid2, simScore)
      }
    }
      .filter(_._3 >= 0.8)
      .toDF("mid1", "mid2", "simScore")

    // 根据电影类别相似度，将电影相似度写入数据库
    try {
      movieRecs.write
        .option("url", mysqlConfig.url)
        .option("dbtable", CONTENT_MOVIE_RECS)
        .option("user", mysqlConfig.user)
        .option("password", mysqlConfig.password)
        .mode("overwrite")
        .format("jdbc")
        .save()
    } catch {
      case e: Exception => e.printStackTrace()
    }

    spark.stop()
  }

  // 计算余弦相似度
  def consinSim(movie1: DoubleMatrix, movie2: DoubleMatrix): Double = {
    movie1.dot(movie2) / (movie1.norm2() * movie2.norm2())
  }
}

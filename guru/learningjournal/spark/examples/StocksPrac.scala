package guru.learningjournal.spark.examples

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object StocksPrac extends Serializable with App {

  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  //Create Spark session
  val sc = SparkSession.builder().appName("Stocks").master("local[3]").getOrCreate()
  //Read your  file
  val carDataFrame = sc.read.option("inferSchema", "true").csv("data\\stocks.csv")

  carDataFrame.schema
  carDataFrame.show(10)

  //Stop the Spark context
  //scala.io.StdIn.readLine()
  sc.stop()


}

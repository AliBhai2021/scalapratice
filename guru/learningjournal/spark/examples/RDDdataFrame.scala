package guru.learningjournal.spark.examples

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object RDDdataFrame extends Serializable with App {

  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  //Create Spark Context
/*
  val sparkAppConf = new SparkConf().setAppName("HelloRDD").setMaster("local[3]")
  val sparkContext = new SparkContext(sparkAppConf)
*/
  val sc = SparkSession.builder().appName("RDDdataFrame").master("local[3]").getOrCreate()
  //Read your  file
  val carDataFrame = sc.read.option("inferSchema", "true")json("data\\cars.json")

  carDataFrame.schema
  carDataFrame.show(10)

  //Stop the Spark context
  //scala.io.StdIn.readLine()
  sc.stop()


}

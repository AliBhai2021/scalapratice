package interviewquestions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.when

object XorientL1 extends App{

  val spark = SparkSession.builder().appName("Xorient").master("local[*]").getOrCreate()
  //
/*
  val filedata = spark.read.textFile("data/logs")
  println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
  filedata.collect().foreach{
    case(x:String) => println(x)
  }

  println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")

  filedata.collect().filter(x=>x.contains("WARNING") | x.contains("Exception")).foreach(println(_))
*/

  println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
  //val newdata = spark.read.textFile("data/logfiletext")
  val newdata = spark.read.textFile("data/logs")
  newdata.printSchema()
  println("----------------")
  newdata.show()
  println("------- Actual Code Start------")
  import org.apache.spark.sql.functions._
  var mydata = newdata.withColumn("label",
    when(col("value").contains("ERROR"), "Error")
    .when(col("value").contains("WARNING"), "Warning")
    .when(col("value").contains("INFO"), "Info")
    .when(col("value").startsWith("EXCE"),"EXCEPTION"))

  mydata.show()
  spark.stop()

}

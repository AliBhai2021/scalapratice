package interviewquestions

import org.apache.spark.sql.SparkSession

object SparkDataframQuestions extends App{

  val spark = SparkSession.builder().master("TEST").master("local[*]").getOrCreate()
  println("#######################################################################################")
  val inputdata = spark.read.format("csv")
    //.option("header","true")
    //.option("delimiter","~")
    .load("/home/lee/practice/SparkPrac/data/res1.csv")
  inputdata.printSchema()
  inputdata.show()
}

package guru.learningjournal.spark.examples.fileReadWritePrac

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataType, DateType}

object Emp100Records extends App{

  val spark = SparkSession.builder().appName("Emp100Records").master("local[*]").getOrCreate()
  import org.apache.spark.sql.functions._
  val data = spark.read.format("csv")
    .option("inferSchema","true")
    .option("header","true")
    .load("data/100Records.csv")
  data.withColumn("Date of Birth", col("Date of Birth").cast(DateType))
    .printSchema()



  data.show(10)

}

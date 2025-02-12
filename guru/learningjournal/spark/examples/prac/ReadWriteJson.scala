package guru.learningjournal.spark.examples.prac

import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.helpers.DateTimeDateFormat
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object ReadWriteJson extends App{

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("Ashraf")
    .getOrCreate()
  val data = Seq(("Ashraf","sales",500),("farsa","sales",3500),("asraf","manager",8000),("asraf1","manager",8300),("asd","marketing",905)
  ,("asd3","marketing",1905))
  import spark.implicits._
  val df = data.toDF("name","dept","sal")
  val wind =Window.partitionBy("dept").orderBy("sal")
  val wind2 = Window.partitionBy("dept")
  val df2 = df.withColumn("data",row_number.over(wind))
    .withColumn("avg",avg(col("sal")).over(wind2))
    .withColumn("sum",sum(col("sal")).over(wind2))
    .withColumn("max",max(col("sal")).over(wind2))
    .withColumn("min",min(col("sal")).over(wind2))
    .where(col("data")===1).select("dept","avg","max","sum","min")
  .show()
  /*df.write.json("D:\\ashraf12345/asf.json")
  df.write.text("D:\\ashraf12345/asf1.txt")
  df.write.csv("D:\\ashraf12345/asf2.csv")*/
}

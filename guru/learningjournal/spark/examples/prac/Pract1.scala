package guru.learningjournal.spark.examples.prac

import java.util.logging.Logger

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{BooleanType, IntegerType}
import org.apache.spark.sql.functions.udf

import scala.io.Source

object Pract1 {

  def findNum(n:Int):Boolean=
    if(n<=1)false
    else if(n==2)true
    else
      !(2 until n).exists(i=> n%i ==0)
  def main(args:Array[String]): Unit ={
  @transient lazy val logger:Logger=Logger.getLogger(getClass.getName)
    val simpledata = Source.fromFile("data/table.txt")
    for(line <- simpledata.getLines()){print(line)}
    val spark = SparkSession.builder().appName("Demo").master("local[3]").getOrCreate()

    val data =spark.read.option("header","true")
      .option("delimiter",",")
      .option("inferSchema","true")
      .csv("data/table.txt")
    data.printSchema()
    data.show()
    data.createOrReplaceTempView("tableData")
    //val df = udf[Boolean,Integer](findNum)
    //spark.sparkContext.register(df)
    //val sqldf =spark.udf.register("isPrimes",isPrimes.asNondeterministic())
    val df = spark.sql("select * from tableData where age == isPrime('age)")

    df.show()
    df.write.option("header","true").option("inferSchema","true").mode("Overwrite").csv("data/table2.csv")
    val rdd = spark.read
      .options(Map("inferSchema"-> "true","header"->"true"))
      .text("data/table.txt")
    rdd.show()
    rdd.printSchema()

    val data2 = spark.read.option("header","true")
      .option("delimiter",",")
      .option("inferSchema","true")
      .csv("data/table2.csv")
    data2.printSchema()
    data2.show()
    spark.stop()
  }
}

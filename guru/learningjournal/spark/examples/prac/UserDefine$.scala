package guru.learningjournal.spark.examples.prac

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

object UserDefine$ {

  def main(args:Array[String]): Unit = {
    @transient lazy val logger:Logger = Logger.getLogger(getClass.getName)
    val spark = SparkSession.builder().appName("Ashraf").master("local[3]")
      .getOrCreate()
    val data = spark.read.option("header", "true")
      .option("delimiter", ",")
      .option("inferSchema", "true")
      .csv("data/table.txt")
    data.show()
    import org.apache.spark.sql.functions.udf
    val num = udf[Boolean, Int](findNum)
    spark.sqlContext.udf.register("isPrime", num)
    data.createOrReplaceTempView("table")
    val df = spark.sql("select * from table where isPrime(age)=='true'")
    logger.info(df.show())
   // df.writeStream.

  }
  def findNum(n:Int): Boolean ={
    if(n<=1)false
    else if(n == 2)true
    else
      !(2 until n).exists(i=> n%i ==0)
  }

}

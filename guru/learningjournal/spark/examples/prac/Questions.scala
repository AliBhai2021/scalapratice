package guru.learningjournal.spark.examples.prac

import org.apache.log4j.Logger
import org.apache.log4j.helpers.DateTimeDateFormat
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object Questions extends Serializable {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    //Create Spark Session
    val spark = SparkSession.builder()
      .appName("Hello DataSet")
      .master("local[3]")
      .getOrCreate()
    import spark.implicits._
    val data = Seq(("a","b","c"),("d","e","f")).toDF("c1","c2","c3") //spark.sparkContext.parallelize(Seq(("a","b","c"),("d","e","f"))).toDF()
    data.printSchema()
    data.show()

    val singleColumn = data.select(concat(col("c1"),lit(','),
                        col("c2"),lit(','),col("c3")).as("singleColumn"))
    singleColumn.show(false)
    singleColumn.select(explode(split(col("singleColumn"),","))).show

    logger.info("Using Spark SQL")

    data.createOrReplaceTempView("dataTable")
    //val query= spark.sql("select concat(c1,',',c2,',',c3) as singleColumn from dataTable")
    val query= spark.sql("select explode(split(concat(c1,',',c2,',',c3),',')) as singleColumn from dataTable")
    query.show()

    // difference between below to print exp
    val rdd1 = spark.sparkContext.parallelize((1 to 100).toList,5)
    rdd1.foreach(print)
    println(":::::::::::::::::::::::::::")
    rdd1.collect().foreach(print)

    // difference between count(*) and count("fieldName)
    val countDataFrame = spark.createDataFrame(Seq((1,"ali"),(2,null),(3,"lee"))).toDF("id","name")
    countDataFrame.select(
      count("*"),
      count(col("name"))
    ).show()

    logger.info("countDataFrame.count() = "+countDataFrame.count())

    val dataDataFrame= Seq("22-08-1992","23-09-1993","23-02-1993","31-12-1993").toDF("date")
    val formatedDate = dataDataFrame.withColumn("date", to_date(col("date"),"dd-MM-yyyy"))
        .withColumn("month",month(col("date")))
        .withColumn("year", year(col("date")))
       .withColumn("dayofmonth(day)", dayofmonth(col("date")))
      .withColumn("dayofweek", dayofweek(col("date")))
      .withColumn("dayofyear(days)", dayofyear(col("date")))
      //.withColumn("days", days(col("date")))
      .withColumn("last_day", last_day(col("date")))
      .withColumn("trunc_month", trunc(col("date"),"month"))
      .withColumn("date_trunc", date_trunc("month",col("date")))


    formatedDate.show()
    spark.stop()
  }

}


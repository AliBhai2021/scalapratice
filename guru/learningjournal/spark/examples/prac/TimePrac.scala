package guru.learningjournal.spark.examples

import org.apache.log4j.Logger
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import java.sql.Date

import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType}

object TimePrac extends Serializable {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Windowing Demo")
      .master("local[3]")
      .getOrCreate()
    import spark.implicits._

    val data = List(("Ali","22-08-1992",595,190000,List("Proddatur","AP")))
    val rdd = spark.sparkContext.parallelize(data)
    import spark.implicits._
    val dataFrame1 = rdd.toDF()
    dataFrame1.printSchema()
    dataFrame1.show

    ///string to date convresion => to_date("column","dd-mm-yyyy")
    rdd.toDF("name","dob","empNo","salary","address").withColumn("date_1", to_date(col("dob"),"dd-mm-yyyy"))
      .withColumn("date_2", col("dob").cast("date")).show
    rdd.toDF("name","dob","empNo","salary","address").withColumn("date_1", to_date(col("dob"),"dd-mm-yyyy"))
      .withColumn("date_2", col("dob").cast("date")).printSchema()


    val schema = StructType(
      Seq(
        StructField("name",StringType, true),
        StructField("dob",DateType,true),
        StructField("empNo",IntegerType,true),
        StructField("salary",IntegerType,true),
        StructField("address",StructType(
          Seq( StructField("town",StringType,true),StructField("state",StringType,true))
        ),true )
      )
    )
    import java.sql.Date
    val dataForRdd = Seq(Row("Ali", Date.valueOf("1992-08-20"), 595, 190000, Row("Proddatur", "AP")),
      Row("Ahammed", Date.valueOf("1993-03-21"), 596, 180000, Row("Kadapa", "AP")),
      Row("Lee", Date.valueOf("1992-09-22"), 597, 170000, Row("Bang", "KA")),
      Row("Bhai", Date.valueOf("1991-02-23"), 598, 160000, Row("HYD", "AP")))
    val dataFrame2 = spark.createDataFrame(spark.sparkContext.parallelize(dataForRdd), schema)
    dataFrame2.show()
    dataFrame2.printSchema()

    dataFrame2.select(col("name"), month(col("dob"))).show

    val filteredData1 = dataFrame2.select(col("name"), col("dob"),
      lag("dob", 1, "").over(Window.partitionBy().orderBy("empNo")).as("date_1"),
      current_date().as("current_date"))
    filteredData1.show
    filteredData1.printSchema()

    dataFrame2.withColumn("rank", dense_rank().over(Window.partitionBy().orderBy(col("salary").desc)))
      .withColumn("lagDate", lag(col("dob"),1).over(Window.partitionBy().orderBy("salary")))
      .withColumn("current_date", current_date())
      .withColumn("current_timestamp", current_timestamp())
      .withColumn("diff_date1", datediff(current_timestamp(),col("dob")))
      .withColumn("datediff_with_current_date", datediff(current_date(),lag(col("dob"),1).over(Window.partitionBy().orderBy("salary"))))
      .withColumn("datediff_with_lap_date", datediff(col("dob"),lag(col("dob"),1).over(Window.partitionBy().orderBy("salary")))).show

    dataFrame2.withColumn("rank", dense_rank().over(Window.partitionBy().orderBy($"salary".desc)))
      .where(col("rank") === 1)
      .show



  }
}

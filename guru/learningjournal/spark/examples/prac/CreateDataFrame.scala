package guru.learningjournal.spark.examples.prac

import java.time.LocalDate
import java.util.Date

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Row, SparkSession, types}
import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._
object CreateDataFrame extends Serializable with App {

  val spark = SparkSession.builder().appName("DF_creation_types").master("local[*]").getOrCreate()

  val data = List(("Ali","22-08-1992",595,190000,List("Proddatur","AP")))
  val rdd = spark.sparkContext.parallelize(data)
  import spark.implicits._
  val dataFrame1 = rdd.toDF()
  dataFrame1.printSchema()
  dataFrame1.show

  // create DataFrame from RowRdd and Schema
/*    val schema = StructType(
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
  val dataForRdd = Seq(Row("Ali",Date.valueOf("1992-08-22"),595,190000,Row("Proddatur","AP")),
    Row("Ahammed",Date.valueOf("1992-09-22"),596,180000,Row("Kadapa","AP")),
    Row("Lee",Date.valueOf("1993-04-02"),597,170000,Row("Bang","KA")),
    Row("Bhai",Date.valueOf("1994-09-22"),598,160000,Row("HYD","AP")))
  val dataFrame2 = spark.createDataFrame(spark.sparkContext.parallelize(dataForRdd),schema)
  dataFrame2.show()
  dataFrame2.printSchema()

  dataFrame2.select(col("name"),month(col("dob"))).show

  val filteredData1 = dataFrame2.select(col("name"),col("dob"),
    lag("dob",1, "").over(Window.partitionBy().orderBy("empNo")).as("date_1"),
    current_date().as("current_date"))
  filteredData1.show
  filteredData1.printSchema()

  dataFrame2.withColumn("rank",dense_rank().over(Window.partitionBy().orderBy(col("salary").desc))).show
  dataFrame2.withColumn("rank",dense_rank().over(Window.partitionBy().orderBy($"salary".desc)))
    .where(col("rank")===1)
    .show
  */

  // create DataFrame from case class RDD
  case class Person(id:Int,name:String,dob:LocalDate)
  val persons=List(Person(1,"ali",LocalDate.parse("2021-01-02")),Person(2,"Ahammed",LocalDate.parse("2021-01-02")),Person(3,"Lee",LocalDate.parse("2021-01-02")))
  val rddPerson=spark.sparkContext.parallelize(persons)
  rddPerson.toDF().printSchema
  rddPerson.toDF().select(col("name")).show
}

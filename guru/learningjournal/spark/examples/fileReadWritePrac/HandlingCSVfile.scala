package guru.learningjournal.spark.examples.fileReadWritePrac

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object HandlingCSVfile extends App {


  val spark = SparkSession.builder()
    .appName("SensorApp")
    .master("local[3]")
    .getOrCreate()
   //spark.sql.columnNameOfCorruptRecord

  val EmployeeInfoDF = spark.read
    .schema("ID Integer,Name String,Salary Integer")
    .option("header",true)
    .option("mode", "PERMISSIVE")
    .option("dateformat","dd.MM.yyyy")
    .csv("data/sampleCsvFile.csv")

  EmployeeInfoDF.show(false)

  val EmployeeInfoDF2 = spark.read
    .schema("ID Integer,Name String,Salary Integer")
    .option("delimiter", ",")
    .option("header",true)
    .option("nanValue", "NaN")
    .option("badRecordsPath","output/")
    .csv("data/sampleCsvFile.csv")
  EmployeeInfoDF2.show(false)



  // ADD COULUMN IN SCHEMA "_corrupt_record String"
  val employee_df = spark.read.option("mode", "PERMISSIVE")
    .schema("ID Integer,Name String,Salary Integer,_corrupt_record String")
    .option("header", true)
    //.option("columnNameOfCorruptRecord", "_corrupt_record")
    .option("nanValue", "NaN")
    .csv("data/sampleCsvFile.csv")

  employee_df.show(false)
}

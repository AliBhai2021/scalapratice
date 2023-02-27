package guru.learningjournal.spark.examples.prac

import guru.learningjournal.spark.examples.prac.HexaTest1.getClass
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

object HandleInvalidRecords extends Serializable {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    //Create Spark Session
    val spark = SparkSession.builder()
      .appName("Hello DataSet")
      .master("local[3]")
      .getOrCreate()
    //=====================================================================

    val EmployeeInfoDF = spark.read
      .schema("ID Integer,Name String,Salary Integer")
      .option("columnNameOfCorruptRecord", "_corrupt_record")
      .option("header",true)
      .option("mode", "PERMISSIVE")
      //.option("mode", "FAILFAST")
      .option("badRecordsPath","tmp")

      .csv("data/sampleCsvFile.csv")
    EmployeeInfoDF.show()

    val data = """{"a": 1, "b":2, "c":3}|{"a": 1, "b":2, "c":3}|{"a": 1, "b, "c":10}""".split('|')
    val corruptDf = spark.read.option("mode", "PERMISSIVE")
      //.option("columnNameOfCorruptRecord", "_corrupt_record")
      .option("badRecordsPath","tmp")
      .json(spark.sparkContext.parallelize(data))
        corruptDf.show()

    spark.stop()
  }

}
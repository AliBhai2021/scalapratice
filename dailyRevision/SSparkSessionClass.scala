package dailyRevision

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object SSparkSessionClass extends App{

  val spark1 = SparkSession.builder().master("local").appName("SampleSparkSessionClass").getOrCreate()
  println("@@@@@@@@@@@@@@@@ spark1 @@@@@@@@@@@@@@@@ "+spark1)

  val spark3 = SparkSession.builder().master("local").appName("SampleSparkSessionClass2").getOrCreate()
  println("@@@@@@@@@@@@@@@@ spark3 @@@@@@@@@@@@@@@@ "+spark3)

 //======================================================================================================
 // Define a list of Row objects
 val data = Seq(
   Row("Alice", 29),
   Row("Bob", 31),
   Row("Cathy", 25)
 )

  // Define the schema using StructType and StructField
  val schema = StructType(List(
    StructField("name", StringType, nullable = true),
    StructField("age", IntegerType, nullable = true)
  ))

  // Create the DataFrame
  val df = spark1.createDataFrame(
    spark1.sparkContext.parallelize(data),
    schema
  )
    df.show()

  println("@@@@@@@@@@@@@@@@@@@@@@ DF SS = "+df.sparkSession)
  println(">>>>>> "+df.colRegex("name"))



}

package guru.learningjournal.spark.examples.fileReadWritePrac.dxc

import java.io.File

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

import scala.collection.parallel.ForkJoinTaskSupport
import scala.concurrent.forkjoin.ForkJoinPool

object SensorFileHandler extends App{
  //val taskSupport = new ForkJoinTaskSupport(new ForkJoinPool(20))
  val dataDir = new File("data/sensorData")

  if (dataDir.exists && dataDir.isDirectory) {
    println(":::::::::::::::::YES::::::::::::::::::")
  }

  var fileList:List[String] = Nil
  var fileCount:Int = 0
  fileCount = dataDir.list.length

  for(file <- dataDir.list)
    fileList = fileList:+file

  println("fileList :"+fileList)
  println("fileCount :"+fileCount)


  if (dataDir.exists && dataDir.isDirectory) {

    val parallel = fileList.par
   // parallel.tasksupport = taskSupport

    parallel.map( file => {

      if (new File(dataDir.getPath() + "/" + file).exists) {
        println("Reading Data File :: " + dataDir.getPath() + "/" + file)
      }
    })
  }

  val spark = SparkSession.builder()
    .appName("SensorApp")
    .master("local[3]")
    .getOrCreate()
  val data = spark.read
    .format("csv")
    .option("mode", "PERMISSIVE")
    .schema("sensor_id String, humidity Integer")
    .option("header", "true")
    //.option("inferSchema", "true")
    .option("columnNameOfCorruptRecord", "_corrupt_record")
    .load("data/sensorData/*")



  data.show(false)
  val invalid_data = data.where(col("humidity") === null)

  val valid_data = data.filter(lit(col("humidity" ))>0).groupBy("sensor_id").agg(avg("humidity").alias("AVG_humidity"),
    min("humidity").alias("min_humidity"),
    max("humidity").alias("max_humidity")).show(false)

  val records_count = data.collect().size
  println("records_count :"+records_count)


val abd = "abfvfk"
  println(abd.split(",").toList)


}

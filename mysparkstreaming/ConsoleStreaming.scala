package mysparkstreaming
//ncat -lk 9999
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object ConsoleStreaming extends App{

  val spark = SparkSession.builder().appName("WordCountUsingStream").master("local[*]").getOrCreate()

  val readInput = spark.readStream.format("socket")
    .option("host","localhost")
    .option("port","9999")
    .load()

  readInput.printSchema()
  val wordsList = readInput.withColumn("words",explode(split(col("value")," ")))
  val wordCount = wordsList.groupBy("words").count()

  val wordcountList = wordCount.writeStream.format("console")
    .option("checkpointLocation","checkpoints")
    .outputMode("complete")
    //.outputMode("update") // console not support update mode
    //.outputMode("append") // console not support update mode with out watermark
    .start()

  wordcountList.awaitTermination()

  scala.io.StdIn.readLine()
  spark.stop()
}

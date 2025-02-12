package mysparkstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger

object OutputModeExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("Output Mode Example")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._  // Importing implicits for encoders

    // Read the stream of input lines from connection to localhost:9999
    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    // Add a timestamp column to the DataFrame
    val linesWithTimestamp = lines.withColumn("timestamp", current_timestamp())

    // Convert lines to Dataset[String] and split into words
    val words = linesWithTimestamp.as[String].flatMap(_.split(" ")).toDF("word", "timestamp")

    // Add watermark on the timestamp column
    val wordsWithWatermark = words.withWatermark("timestamp", "10 minutes")

    // Count the occurrences of each word
    val wordCounts = wordsWithWatermark.groupBy("word").count()

    // Complete Mode: Entire result table for word counts
    val completeQuery = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .option("truncate", "false")
      .trigger(Trigger.ProcessingTime("10 seconds"))  // Set trigger interval to 10 seconds
      .start()

    // Append Mode: Only new lines
    val appendQuery = lines.writeStream
      .outputMode("append")
      .format("console")
      .option("truncate", "false")
      .start()

    // Wait for the queries to finish
    spark.streams.awaitAnyTermination()
  }
}

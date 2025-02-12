package guru.learningjournal.spark.examples.prac

import java.util.logging.Logger

import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object Prac5 extends App{
  @transient  lazy val  logger:Logger=Logger.getLogger(getClass.getName)
  val spark = SparkSession.builder().appName("Ashraf").master("local[3]")
    .config("spark.streaming.stopGracefullyOnShutdown","true")
    .config("spark.sql.shuffle.partitions",3)
    .getOrCreate()

  val lineDF= spark.readStream
    .format("socket")
    .option("host","localhost")
    .option("port","9999")
    .load()
  //lineDF.printSchema()
  val wordDF = lineDF.select(expr("explode(split(value,' ')) as word"))
  val count = wordDF.groupBy("word").count()
  val wordCountQuery = count.writeStream
    .format("console")
    .option("checkpointLocation","chk-point-dir")
    .outputMode("complete")
    .start()

logger.info("Listening to localhost:9999")
  wordCountQuery.awaitTermination()

}

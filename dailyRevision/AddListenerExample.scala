package dailyRevision

import org.apache.spark.sql.SparkSession
import org.apache.spark.scheduler.{SparkListener, SparkListenerJobStart}

object AddListenerExample {
  def main(args: Array[String]): Unit = {
    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("AddListenerExample")
      .master("local[*]")
      .getOrCreate()

    // Define a custom listener
    class MyCustomListener extends SparkListener {
      override def onJobStart(jobStart: SparkListenerJobStart): Unit = {
        println(s"Job ${jobStart.jobId} started at ${jobStart.time}")
        println(s"stageIds ${jobStart.stageIds} started at ${jobStart.time}")
      }
    }

    // Add the custom listener to SparkContext
    spark.sparkContext.addSparkListener(new MyCustomListener())

    // Example DataFrame and operations
    val data = Seq((1, "Alice"), (2, "Bob"), (3, "Cathy"))
    val df = spark.createDataFrame(data).toDF("id", "name")

    // Perform some operations to trigger job execution
    import spark.implicits._
    df.filter($"id" > 1).show()
    df.groupBy("name").count().show()

    // Stop the Spark session
    spark.stop()
  }
}

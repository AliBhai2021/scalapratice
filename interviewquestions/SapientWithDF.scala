package interviewquestions
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window

//Question : use spark batch to add an additional column and generate session ids
object SapientWithDF extends App {

  // Create SparkSession
  val spark = SparkSession.builder().master("local[*]")
    .appName("Sessionization with Spark")
    .getOrCreate()
  import  spark.implicits._
  spark.sparkContext.setLogLevel("ERROR")

  // Sample DataFrame with user activities
  val data = Seq(
    (1, "2024-04-22T08:00:00", "page_view"),
    (1, "2024-04-22T08:05:00", "page_view"),
    (1, "2024-04-22T08:20:00", "click"),
    (1, "2024-04-22T08:30:00", "page_view"),
    (2, "2024-04-22T08:10:00", "page_view"),
    (2, "2024-04-22T08:15:00", "click"),
    (2, "2024-04-22T08:25:00", "page_view"),
    (3, "2024-04-23T08:25:00", "lee"),
    (3, "2024-04-23T09:25:00", "lee"),
    (3, "2024-04-23T09:30:00", "lee"),
    (3, "2024-04-23T09:35:00", "lee"),
    (3, "2024-04-23T10:25:00", "lee")
  ).toDF("user_id", "timestamp", "activity")

  // Define window specification
  val windowSpec = Window.partitionBy("user_id").orderBy("timestamp")

  // Define session threshold (in seconds)
  val sessionThreshold = 15 * 60 // 15 minutes

  // Add a column to mark session boundaries
  val sessionBoundaries = when(
    unix_timestamp($"timestamp") - lag(unix_timestamp($"timestamp"), 1)
      .over(windowSpec) > sessionThreshold,
    1
  ).otherwise(0)

  // Generate session IDs using cumulative sum
  val sessionId = sum(sessionBoundaries).over(windowSpec).as("session_id")

  // Add session ID column to the DataFrame
  val result = data
    .withColumn("timestamp", to_timestamp($"timestamp"))
    .withColumn("session_id", sessionId)

  // Show the result
  println("Cummulative active sessions per user")
  result.show()
  //====================================================================================================================
  // Convert timestamp to timestamp type
  val dataWithTimestamp = data.withColumn("timestamp", to_timestamp($"timestamp"))

  val dataWithBoundaries = dataWithTimestamp.withColumn("session_boundary", sessionBoundaries)

  // Calculate session ID
  val dataWithSessionId = dataWithBoundaries.withColumn("session_id", sessionId)

  // Group by date and count distinct session IDs
  val sessionsPerDay = dataWithSessionId
    .groupBy(date_format($"timestamp", "yyyy-MM-dd").alias("session_date"))
    .agg(countDistinct("session_id").alias("sessions_count"))

  // Show the result
  println("Cummulative active sessions per day")
  sessionsPerDay.show()

  //============================================================================================
  // Group by user_id and date, calculate total time spent by the user in seconds
  val timeSpentPerUserPerDay = dataWithTimestamp
    .groupBy($"user_id", date_format($"timestamp", "yyyy-MM-dd").alias("date"))
    .agg((max($"timestamp").cast("long") - min($"timestamp").cast("long")).alias("total_time_spent"))

  // Show the result
  println("total time spent by the user in seconds")
  timeSpentPerUserPerDay.show()

}

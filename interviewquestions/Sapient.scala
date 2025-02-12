package interviewquestions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object Sapient extends App{

  val spark = SparkSession.builder().master("local[*]").appName("Queation1").getOrCreate()
  import spark.implicits._
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
  val sessionThreshold = 15 * 60 // 15 minutes -> 900 sec

  // Add a column to mark session boundaries
  val sessionBoundaries = when(
    unix_timestamp($"timestamp") - lag(unix_timestamp($"timestamp"), 1).over(windowSpec) > sessionThreshold,
    1
  ).otherwise(0)

  // Generate session IDs using cumulative sum
  val sessionId = sum(sessionBoundaries).over(windowSpec).as("session_id")

  //============== My debugg
  val diff_time = unix_timestamp($"timestamp") - lag(unix_timestamp($"timestamp"), 1).over(windowSpec)

  // Add session ID column to the DataFrame
  val result = data
    .withColumn("timestamp", to_timestamp($"timestamp"))
    .withColumn("session_id", sessionId)
    .withColumn("sessionBoundaries",sessionBoundaries)
      .withColumn("diff_time",diff_time)
      .withColumn("sessionThreshold",lit(sessionThreshold))

  // Show the result
  result.show()

  //=========================================== SPARK SQL ================================================
  data.createOrReplaceTempView("user_activities")
  data.printSchema()
  val sessionizationQuery =
    s"""
       |  SELECT
       |    user_id,
       |    timestamp,
       |    activity,
       |    unix_timestamp(timestamp) - lag(unix_timestamp(timestamp), 1) OVER (PARTITION BY user_id ORDER BY timestamp) AS session_value,
       |    CASE WHEN unix_timestamp(timestamp) - lag(unix_timestamp(timestamp), 1) OVER (PARTITION BY user_id ORDER BY timestamp) > $sessionThreshold THEN 1 ELSE 0 END AS session_boundary
       |  FROM user_activities
     """.stripMargin

  val result2 = spark.sql(sessionizationQuery)
  result2.show()

  //================================================================================
  val sessionizationQuery3 =
    s"""
       |WITH session_boundaries AS (
       |  SELECT
       |    user_id,
       |    timestamp,
       |    activity,
       |    CASE WHEN unix_timestamp(timestamp) - lag(unix_timestamp(timestamp), 1) OVER (PARTITION BY user_id ORDER BY timestamp) > $sessionThreshold THEN 1 ELSE 0 END AS session_boundary
       |  FROM user_activities
       |)
       |
       |SELECT
       |  user_id,
       |  timestamp,
       |  activity,
       |  SUM(session_boundary) OVER (PARTITION BY user_id ORDER BY timestamp) AS session_id
       |FROM session_boundaries
     """.stripMargin

  val result3 = spark.sql(sessionizationQuery3)

  result3.show()

}

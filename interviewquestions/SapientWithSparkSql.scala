package interviewquestions

object SapientWithSparkSql extends App{
  import org.apache.spark.sql.SparkSession

  val spark = SparkSession.builder()
    .appName("Sessionization with Spark SQL")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  // Create a DataFrame with user activities
  val data = Seq(
    (1, "2024-04-22T08:00:00", "page_view"),
    (1, "2024-04-22T08:05:00", "page_view"),
    (1, "2024-04-22T08:20:00", "click"),
    (1, "2024-04-22T08:30:00", "page_view"),
    (2, "2024-04-22T08:10:00", "page_view"),
    (2, "2024-04-22T08:15:00", "click"),
    (2, "2024-04-22T08:25:00", "page_view")
  ).toDF("user_id", "timestamp", "activity")

  data.createOrReplaceTempView("user_activities")

  val sessionThreshold = 15 * 60 // 15 minutes

  var sessionizationQuery =
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

  var result = spark.sql(sessionizationQuery)

  result.show()

  //==========================================================
  sessionizationQuery =
    s"""
       |WITH session_boundaries AS (
       |  SELECT
       |    user_id,
       |    timestamp,
       |    activity,
       |    CASE WHEN unix_timestamp(timestamp) - lag(unix_timestamp(timestamp), 1) OVER (PARTITION BY user_id ORDER BY timestamp) > $sessionThreshold THEN 1 ELSE 0 END AS session_boundary
       |  FROM user_activities
       |),
       |session_with_session_id AS (
       |  SELECT
       |    user_id,
       |    timestamp,
       |    activity,
       |    SUM(session_boundary) OVER (PARTITION BY user_id ORDER BY timestamp) AS session_id
       |  FROM session_boundaries
       |),
       |sessions_per_day AS (
       |  SELECT
       |    date(timestamp) AS session_date,
       |    COUNT(DISTINCT session_id) AS sessions_count
       |  FROM session_with_session_id
       |  GROUP BY date(timestamp)
       |)
       |
       |SELECT * FROM sessions_per_day
     """.stripMargin

  result = spark.sql(sessionizationQuery)

  result.show()

}

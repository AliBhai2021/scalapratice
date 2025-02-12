package interviewquestions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window


object EmployeeLoginOut extends App{

  val spark = SparkSession.builder()
    .master("local[*]")
    .appName("EmployeeActiveSession")
    .getOrCreate()

  import org.apache.spark.sql.types._
  val customSchema = StructType(Seq(
    StructField("user_id", IntegerType, nullable = true),
    StructField("activity", StringType, nullable = true),
    StructField("timestamp", TimestampType, nullable = true)
  ))

  val inputdata = spark.read.format("csv")
    .option("header","true")
    .option("inferSchema", "false")
    .option("timestampFormat", "yyyy-MM-dd'T'HH:mm:ss") //specify the required format time
    .schema(customSchema)
    .load("data/mydata/employee_login_out_data.csv")

  inputdata.printSchema()
  inputdata.orderBy("user_id","timestamp")show()

  val windowSpec = Window.partitionBy("user_id", "dayDate").orderBy("timestamp")
  val sessionThreshold = 15 * 60 // 15 minutes -> 900 sec

  import org.apache.spark.sql.functions._
  val customData1 = inputdata.withColumn("dayDate", substring(col("timestamp"),1,10) )
    .withColumn("startTime", inputdata("timestamp"))
    .withColumn("endTime", lead("timestamp",1).over(windowSpec) )
    .withColumn("durationInSec", unix_timestamp(col("endTime")) - unix_timestamp(col("startTime") ))
    .withColumn("session", when(col("durationInSec")>sessionThreshold, 1).otherwise(0)) // if outing time is less than 15min consider as same session (no Gap)
    //.select("*").where(col("activity") === "in")
    //.select("*").where(col("activity") === "out")
    //.withColumn("sessionId", lit(sum("session").over(windowSpec))) // it is depend on either "in" or "out"
  customData1.show()

  val userOutSessionData = customData1.filter(col("activity") === "out")
    .withColumn("sessionId", lit(sum("session").over(windowSpec))) // it is depend on either "in" or "out"
  userOutSessionData.show()

  val userInSessionData = customData1.select("*").where(col("activity") === "in")
    .withColumn("sessionId", lit(sum("session").over(windowSpec))) // it is depend on either "in" or "out"
  userInSessionData.show()

  // Print total sessions generated on each day per user
  userOutSessionData.groupBy("user_id","dayDate")
    .agg(max("sessionId").alias("maxSession"),
      sum("durationInSec").alias("TotalOutTime")
    )
    .withColumn("inMinutes", col("TotalOutTime")/60)
    .show()
}

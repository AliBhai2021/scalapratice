package sparkPratice

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Customer_Sales_Product extends App{
  val spark = SparkSession.builder().appName("Lee").master("local[*]").getOrCreate()
  import spark.implicits._
  spark.sparkContext.setLogLevel("ERROR")
  val inputData = spark.read.format("CSV")
    .option("header","true")
    .load("data/mydata/Sales_product_customer.csv")

  inputData.show()

  val dataframe1 = inputData.groupBy(col("customer_id"))
  //dataframe1.show()
  val aggregated_df = dataframe1.agg(collect_list("product_name").alias("product_name"))
  aggregated_df.show()
}

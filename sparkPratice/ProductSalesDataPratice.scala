package sparkPratice

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object ProductSalesDataPratice extends App{

  val spark =SparkSession.builder().appName("ProductSales").master("local[*]").getOrCreate()

  val productSalesData = spark.read.format("csv")
    .option("header","true")
    .option("inferSchema","true")
    .option("dateFormat", "yyyy-MM-dd")
    .load("data/sample_product_sales.csv")
  productSalesData.printSchema()

  val productSalesData2 = productSalesData.withColumn("SaleDate", to_date(col("SaleDate"), "yyyy-MM-dd"))
      .withColumn("TotalSoldPrice", col("QuantitySold")*col("Price"))

  productSalesData2.printSchema()

  val psd_df2 = productSalesData2.groupBy("ProductID", "City", "ProductName", "Category").agg(
    sum("QuantitySold").alias("SumQuantitySold")
    ,sum("TotalSoldPrice").alias("SumTotalSoldPrice")
  ).orderBy("City")
  psd_df2.show()

  // Calculate the maximum price per city
  var maxPricePerCity = productSalesData2
    .groupBy("City")
    .agg(max("Price").alias("maxPrice"))
  maxPricePerCity.show()

  // Join the original DataFrame with the max price DataFrame to get product details
  val result = productSalesData2
    .join(maxPricePerCity, Seq("City"))
    .filter(col("Price") === col("maxPrice"))
    //.select("ProductName", "City", "Price")
  result.show()

  // Define a WindowSpec partitioned by City
  val windowSpec = Window.partitionBy("City")
  // Calculate max price per City using window function
  maxPricePerCity = productSalesData2
    .withColumn("maxPrice", max("Price").over(windowSpec))
    .filter(col("Price") === col("maxPrice"))
    .drop("maxPrice")

  // Show the result
  maxPricePerCity.show()

  val recentTranaction = productSalesData2.filter(col("City").isNotNull)
    .withColumn("recentTA", row_number().over(Window.partitionBy("ProductName").orderBy( desc("SaleDate"))))
    .filter(col("recentTA") === 1)
    .show()


}

package interviewquestions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/*
============ TRANSACTION TABLE ============
CUST_ID	TRANSACTION_TYPE
TRN_AMOUNT
1	CREDIT	30
1	DEBIT	40
2	CREDIT	50
3	CREDIT	60
2	DEBIT	70
3	DEBIT	80

AMOUNT_TABLE
CUST_ID	TOTAL_AMOUNT
1	1000
2	2000
3	3000
4	5000
Calculate the final balance for each customers based on the transaction type
*/


object Capgemni_L2 extends App{
  // Initialize Spark session
  val spark = SparkSession.builder()
    .appName("Transaction Balance Calculation").master("local[*]")
    .getOrCreate()

  import spark.implicits._

  // Define the schema and read the transaction data
  val transactionData = Seq(
    (1, "CREDIT", 30),
    (1, "DEBIT", 40),
    (2, "CREDIT", 50),
    (3, "CREDIT", 60),
    (2, "DEBIT", 70),
    (3, "DEBIT", 80)
  ).toDF("CUST_ID", "TRANSACTION_TYPE", "TRN_AMOUNT")

  // Define the schema and read the amount data
  val amountData = Seq(
    (1, 1000),
    (2, 2000),
    (3, 3000),
    (4, 5000)
  ).toDF("CUST_ID", "TOTAL_AMOUNT")

  // Aggregate transactions to calculate total credit and debit amounts per customer
  val transactionSummary = transactionData.groupBy("CUST_ID")
    .agg(
      sum(when($"TRANSACTION_TYPE" === "CREDIT", $"TRN_AMOUNT").otherwise(0)).as("TOTAL_CREDIT"),
      sum(when($"TRANSACTION_TYPE" === "DEBIT", $"TRN_AMOUNT").otherwise(0)).as("TOTAL_DEBIT")
    )
  transactionSummary.show()

  // Join the transaction summary with the amount table
  val joinedData = transactionSummary.join(amountData, Seq("CUST_ID"), "right")
    .na.fill(0, Seq("TOTAL_CREDIT", "TOTAL_DEBIT"))

  joinedData.show()

  // Calculate the final balance
  val finalBalance = joinedData.withColumn("FINAL_BALANCE",
    $"TOTAL_AMOUNT" + $"TOTAL_CREDIT" - $"TOTAL_DEBIT"
  )

  finalBalance.show()
  // Show the final balance for each customer
  finalBalance.select("CUST_ID", "FINAL_BALANCE").show()

  spark.stop()

}

package databricks

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object TransposeExample extends App{

  val spark = SparkSession.builder().master("local[*]").appName("Transpose").getOrCreate()
  import spark.implicits._

  def TransposeDF(df: DataFrame, columns: Seq[String], pivotCol: String): DataFrame = {
    val columnsValue = columns.map(x => "'" + x + "', " + x)
    val stackCols = columnsValue.mkString(",")
    val df_1 = df.selectExpr(pivotCol, "stack(" + columns.size + "," + stackCols + ")")
      .select(pivotCol, "col0", "col1")

    val final_df = df_1.groupBy(col("col0")).pivot(pivotCol)
      .agg(sum(col("col1")))
      .withColumnRenamed("col0", pivotCol)
    final_df
  }


  val DataSeq = Seq(("Shirts", 10, 13, 34, 10), ("Trousers", 11, 2, 30, 20), ("Pants", 70, 43, 24, 60),("Pants", 0, 7, 0, 0), ("Sweater", 101, 44, 54, 80))
  import spark.implicits._
  val productQtyDF = DataSeq.toDF("Products", "Small", "Medium", "Large", "ExLarge")
  productQtyDF.show()

  val productTypeDF = TransposeDF(productQtyDF, Seq("Small", "Medium", "Large", "ExLarge"), "Products")
  productTypeDF.show(false)
}

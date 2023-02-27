package guru.learningjournal.spark.examples.prac

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
object ConditionsPrac extends Serializable with App {

  val spark = SparkSession.builder().appName("Conditions_prac").master("local[*]").getOrCreate()
  case class AccountData(id:Int, name:String,transaction:String,amount:Int)
  val accountData = List(AccountData(101,"Ali","debit",100),AccountData(101,"Ali","credit",200),AccountData(101,"Ali","debit",50),
    AccountData(102,"Lee","debit",200),AccountData(102,"Lee","credit",200),AccountData(102,"Lee","debit",50),
    AccountData(103,"Bhai","debit",100),AccountData(103,"Bhai","credit",500),AccountData(103,"Bhai","debit",50))
  import spark.implicits._
  val rdd = spark.sparkContext.parallelize(accountData).toDF()

  rdd.withColumn("check_1", when(col("transaction") === "debit", col("amount")*(-1)).otherwise(col("amount")) )
    .groupBy("id").agg(sum(col("check_1")).as("balance"))
    .show

  val df2 = rdd.groupBy(col("id")).pivot(col("transaction")).agg(sum("amount"))
  df2.show
  df2.withColumn("balance",col("credit")-col("debit")).show

  df2.withColumn("balance",col("credit")-col("debit")).drop("debit","credit").show

  val consequenceRdd = spark.sparkContext.parallelize(List((1,"A"),(2,"A"),(3,"A"),(4,"B"),(5,"B"),(6,"A"),(7,"A"))).toDF("id","name")
  consequenceRdd.select(col("name"),
    lit(row_number().over(Window.partitionBy().orderBy(col("id")))).as("rep1")).show
  consequenceRdd.select(col("name"),
    lit(row_number().over(Window.partitionBy("name").orderBy("id")).as("rep2"))).show
  consequenceRdd.select(col("name"),
    (lit(row_number().over(Window.partitionBy().orderBy(col("id"))) - lit(row_number().over(Window.partitionBy("name").orderBy("id"))).as("repeat")))
  ).show

/*
  consequenceRdd.select(col("name"),
    (lit(row_number().over(Window.partitionBy().orderBy(col("id"))) - lit(row_number().over(Window.partitionBy("name").orderBy("id"))).as("repeat2")))
  ).groupBy(col("repeat2"),col("name")).sum("repeat2").show
*/

  consequenceRdd.as("t1").join(consequenceRdd.as("t2"), col("t1.id")===col("t2.id")+1 && col("t1.name")===col("t2.name"),"inner")
  .join(consequenceRdd.as("t3"), col("t1.id")===col("t3.id")+1 && col("t1.name")===col("t3.name"),"inner")
    .select(col("t1.name")).show
}

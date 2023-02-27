package guru.learningjournal.spark.examples

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object WindowPrac extends Serializable {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Windowing Demo")
      .master("local[3]")
      .getOrCreate()
    import spark.implicits._
    case class Salary(depName: String, empNo: Long, salary: Long)

    val empsalary = Seq(
      ("sales", 1, 5000),
      ("personnel", 2, 3900),
      ("sales", 3, 4800),
      ("sales", 4, 4800),
      ("personnel", 5, 3500),
      ("develop", 7, 4200),
      ("develop", 8, 6000),
      ("develop", 9, 4500),
      ("develop", 10, 5200),
      ("develop", 11, 5200)).toDF("depName","empNo","salary")

    val demo1 = empsalary.withColumn("Agg", sum("salary")
      .over(Window.partitionBy("depName").orderBy("empNo")))
    demo1.show

    val demo1_1 = empsalary.withColumn("Total_Agg", sum("salary")
      .over(Window.partitionBy().orderBy("empNo")))
    demo1_1.show

    val demo2 = empsalary.withColumn("Lag",lag("salary",1,0)
    .over(Window.partitionBy("depName").orderBy("empNo")))
    demo2.show

  }

}

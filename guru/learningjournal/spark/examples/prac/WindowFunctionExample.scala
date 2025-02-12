package guru.learningjournal.spark.examples.prac

import org.apache.log4j.Logger
import org.apache.spark.sql.{Encoders, SparkSession}

object WindowFunctionExample extends Serializable with App {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)
    val spark = SparkSession.builder()
      .appName("Windowing Demo")
      .master("local[3]")
      .getOrCreate()
    import spark.implicits._

  case class Salary(depName: String, empNo: Long, salary: Long)
  val empsalary = Seq(
    Salary("sales", 1, 5000),
    Salary("personnel", 2, 3900),
    Salary("sales", 3, 4800),
    Salary("sales", 4, 4800),
    Salary("personnel", 5, 3500),
    Salary("develop", 7, 4200),
    Salary("develop", 8, 6000),
    Salary("develop", 9, 4500),
    Salary("develop", 10, 5200),
    Salary("develop", 11, 5200))//.toDF("depName","empNo","salary")
  val empDataFrame = spark.createDataFrame(empsalary)

  empDataFrame.sort("salary").show()

  val SalaryEncoder = Encoders.product[Salary]
}

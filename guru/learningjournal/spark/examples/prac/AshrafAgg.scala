package guru.learningjournal.spark.examples.prac

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object AshrafAgg {
  def main(args:Array[String]): Unit ={
    val spark = SparkSession.builder().appName("AggFunction").master("local[3]").getOrCreate()
    val data = Seq(("James","Sales",3000),
      ("Michael","Sales",4600),
      ("Robert","Sales",4100),
      ("Maria","Finance",3000),
      ("Raman","Finance",3000),
      ("Scott","Finance",3300),
      ("Jen","Finance",3900),
      ("Jeff","Marketing",3000),
      ("Kumar","Marketing",2000)
    )
    import spark.implicits._
    val df = data.toDF("name","dept","sal")
    //df.show()
    val w1 = Window.partitionBy("dept").orderBy("sal")
    df.withColumn("row", row_number.over(w1))
      .where($"row" === 1 ).drop("row")
      //.show()

     df.createOrReplaceTempView("data1")
      val df1 = spark.sql(" select dept,max(sal) from data1 group by dept having max(sal) ")
    val df2 = spark.sql("select * from  (select dept, max(sal)" +
      "from data1 group by dept having max(sal))")
    //df1.show()
    df2.show()
    spark.close()
  }
}
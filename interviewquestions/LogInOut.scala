 package interviewquestions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window

object LogInOut extends App {

  val spark = SparkSession.builder().appName("SessionTime").master("local[2]").getOrCreate()
  import spark.implicits._
  val data = (1 to 12).toList.zip(List("a","a","b","b","b","b","c","a","a","b","b"))
  println(data.mkString(" "))
  println(data)
  val df = data.toDF("id","name")

  df.foreach(print(_))
  df.show()

  df.createOrReplaceTempView("t")
  spark.sqlContext.sql( "select t.id,t.name," +
    "row_number() over (order by id) as rwid1," +
    "row_number() over (order by name) as rwid3," +
    "row_number() over (partition by name order by id) as rwid2," +
    "(row_number() over (order by id) - row_number() over (partition by name order by id)) as grp from t  ").show()
  import org.apache.spark.sql.functions._

  df.createOrReplaceTempView("t")
  spark.sqlContext.sql("select name, count(*) from (" +
    "select t.*,(row_number() over (order by id) - row_number() over (partition by name order by id)) as grp from t " +
    ") t group by grp, name").show()

  //usin Data Frame
  df.withColumn( "col2", lit(row_number().over(Window.partitionBy("name").orderBy("id"))))
    .withColumn("col3", col("id")-col("col2"))
    .groupBy("name","col3").count().as("cnt")
    //.select("name", "cnt")
    .show()

  //============================================= login / logoff ===========================================
  println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val worksession = List((10.01,"on"),(10.02,"on"),(10.03,"on"),(10.04,"off"),(10.07,"on"),(10.08,"on"),(10.09,"off"))
    .toDF("time","status")

  import org.apache.spark.sql.functions._
  val df2 = worksession.withColumn("id",monotonically_increasing_id())
  df2.show()

  worksession.toDF("time","status")
    .withColumn("orid",lit(row_number().over(Window.orderBy("time"))))
    .withColumn("partition",row_number().over(Window.partitionBy("status").orderBy("time")))
    .withColumn("grpkey", col("orid")-col("partition") )
      .show(false)

  worksession.toDF("time","status")
    .withColumn("grp", lit(row_number().over(Window.orderBy("time"))-
      row_number().over(Window.partitionBy("status").orderBy("time"))) )
    .groupBy("grp")
    .agg(
      max("status") as "status",
      min("time") as "time"
    ).orderBy("time").show()

  worksession.toDF("time","status")
    .withColumn("grp", lit(row_number().over(Window.orderBy("time"))-
      row_number().over(Window.partitionBy("status").orderBy("time"))) )
    .groupBy("grp")
    .agg(
      max("status") as "status",
      min("time") as "time"
    ).orderBy("time")
    .withColumn("logoff", lead("time",1).over(Window.orderBy("time")))
    .show(false)

  worksession.toDF("time","status")
    .withColumn("grp", lit(row_number().over(Window.orderBy("time"))-
      row_number().over(Window.partitionBy("status").orderBy("time"))) )
    .groupBy("grp")
    .agg(
      max("status") as "status",
      min("time") as "time"
    ).orderBy("time")
    .withColumn("logoff", lead("time",1).over(Window.orderBy("time")))
    .withColumnRenamed("time","login")
    .filter(col("status") === "on")
    .drop("grp","status")
    .show()

}

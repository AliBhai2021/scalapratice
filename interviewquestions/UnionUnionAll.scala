package interviewquestions

import org.apache.spark.sql.SparkSession

object UnionUnionAll extends App{

  val spark = SparkSession.builder().master("local[*]").appName("TEST").getOrCreate()
  import spark.implicits._
  //val df1 = spark.sparkContext.parallelize(List(1,0,0,1)).toDF("id")
  val df1 = Seq(1,0,1).zip(Seq("a","a","a")).toDF("id","lee")
  val df2 = Seq(1,0,1).zip(Seq("a","a","a")).toDF("id","lee")

  df1.printSchema()
  df2.printSchema()

  df1.show()
  df2.show()
  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val df3 = df1.union(df2).distinct()
  df3.show(false)

  val df4= df1.unionAll(df2).distinct()
  df4.show(false)

  val df5 =df3.dropDuplicates()
    //df5.show("false")

  df1.createOrReplaceTempView("ddf1")
  df2.createOrReplaceTempView("ddf2")

  println("Performin UNION operation")
  spark.sqlContext.sql("""select * from ddf1 union select * from ddf2""")
    .show()
  println("Performin UNION ALL operation")
  spark.sqlContext.sql("""select * from ddf1 union all select * from ddf2""")
    .show()

  //spark sql dataframe
  println("Spark SQL DataFrame : UNION")
  val dfsql1 = spark.sqlContext.sql("""select * from ddf1 """)
  val dfsql2= spark.sqlContext.sql("""select * from ddf2 """)

  dfsql1.unionAll(dfsql2).show()
  println("Spark SQL DataFrame : UNION ALL")
  dfsql1.unionAll(dfsql2).show()

  val sampledf = spark.sqlContext.sql(
    """select '1' col1, '2' col2, '3' col3 union all
      |select '1', '2', '3'
      |""".stripMargin)

  sampledf.show()

}

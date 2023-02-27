package interviewquestions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.log4j.Logger
import org.apache.spark.sql.expressions.Window

object Mphasis extends App with Serializable {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  val spark = SparkSession.builder().master("local[*]").appName("Mphasis").getOrCreate()
  import spark.implicits._
  val list = spark.sparkContext.parallelize(List(("John","Science",80),("Roy","Maths",90),("John","English",90),("Roy","Environment",50),("John","Science",90)))
  val agg_rdd = list.map(x=>(x._1,x._3)).aggregateByKey((0,0))((acc, value) => (acc._1 + value, acc._2 + 1),(acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
  val sum = agg_rdd.mapValues(x => (x._1/x._2))
  val result1 =sum.collect
  result1.foreach(println(_))
  //method #2

  val rddlist = list.groupBy(_._1).mapValues(x=> x.map(_._3).sum/x.size)
  rddlist.collect().foreach(println(_))


  val processedRDD = list.map(x=>(x._1,x._3)).groupByKey.mapValues{iterator => iterator.sum / iterator.size}
  val result2 = processedRDD.collect.toList
  result2.foreach(println(_))

  val reduce_rdd = list.map(x=> (x._1,x._3)) //:(John,80)#(Roy,90)#(John,90)#(Roy,50)#(John,90)
      .mapValues(y=> (y,1)).reduceByKey((a,b)=>(a._1+b._1, a._2+b._2))
      .map(z=> (z._1, z._2._1/z._2._2))

  println("reduce_rdd :"+reduce_rdd.collect().mkString("#"))
  // Via DataFrames

  val list2 = List(("John","Science",80),("Roy","Maths",90),("John","English",90),("Roy","Environment",50),("John","Science",90))
    .toDF("name","subject","marks")

  val aggdf = list2.groupBy("name").avg("marks").alias("aggMarks")
  aggdf.show()

 // val aggdf2 = list2.groupBy("name").agg()

  //aggdf.select("name","average_marks")show()

//==================== Using Scala collection(), RDD, DF =========================================================================================================
  //Example 2:
  // using scala collection
  logger.info(":::::::::::::::: Scala Collection :::::::::::::::")
  println(":::::::::::::::: Scala Collection :::::::::::::::")
  val list3 = List(("A",10),("B",20),("A",20),("c",30))
  val list3res1 = list3.groupBy(_._1).mapValues(x=> (x.map(_._2).sum/ x.length))
  println(list3res1.toList)
  println(list3.groupBy(_._1))
  logger.info(":::::::::::::::: RDD Collection :::::::::::::::")
  println(":::::::::::::::: RDD Collection :::::::::::::::")

  // using RDD
  val processedRDD2 = spark.sparkContext.parallelize(List(("A",10),("B",20),("A",20),("c",30)))
  val processedRDD3 = processedRDD2.groupByKey.mapValues{iterator => iterator.sum / iterator.size}
  val resultrdd2 = processedRDD3.collect.toList
  resultrdd2.foreach(println(_))
  //filter sum >50
  println("----------------------------------------------------------------------")
  val filterresult= processedRDD2.groupBy(x=> x._1).mapValues(x=> x.map(_._2).sum)
  println(filterresult.collect().toList)

  // using DF
  logger.info(":::::::::::::::: DataFrame :::::::::::::::")
  println(":::::::::::::::: DataFrame :::::::::::::::")

  import spark.implicits._
  val list4df = List(("A",10),("B",20),("A",20),("C",30),("C",10),("B",60),("A",30)).toDF("c1","c2")
  val list4res1 = list4df.groupBy("c1").agg(avg("c2").alias("avg_marks"),
    min("c2").alias("min"),
    max("c2").alias("MAX")
    //sum("c2").alias("SUM")
  )
  list4res1.show()
  // pair(key,value)

  val list5df = List(("A",10),("B",80),("C",20),("D",80),("E",10),("F",60),("G",30))
  val max_element  = list5df.map(_._2).max // return only value
  println("max_element "+max_element)

  println(list5df.maxBy(_._2))  // return (key,value)

  //print keys of max values from pairs
  println("filter :"+list5df.filter(_._2 == list5df.maxBy(_._2)._2))

//find the avg of salary (city and depart) wise and print max avg salary of dept from each city
val test1 = List(("emp1","city1","dept1",200),("emp2","city1","dept1",100),("emp3","city1","dept2",100),("emp4","city1","dept2",50),
  ("emp5","city2","dept1",200),("emp6","city2","dept1",100),("emp7","city2","dept2",100),("emp8","city2","dept2",50))
  .toDF("empname","city","dept","salary")
  test1.groupBy("city","dept").agg(avg("salary").alias("avgSalary")).show(false)
  test1.groupBy("city","dept").agg(avg("salary").alias("avgSalary"))
    . groupBy("city").max("avgSalary")
    .show(false)


  val emp = List(("emp1","city1","dept1",200),("emp2","city1","dept1",100),("emp3","city1","dept2",100),("emp4","city1","dept2",50),
    ("emp5","city2","dept1",200),("emp6","city2","dept1",100),("emp7","city2","dept2",100),("emp8","city2","dept2",50))
    .toDF("empname","city","dept","salary")
  emp.groupBy("city","dept").agg(avg("salary").alias("avgSalary"))
    .withColumn("rank" ,lit(dense_rank().over(Window.partitionBy("city")
                                  .orderBy($"avgSalary".desc))))
    //.where("rank ==1")
    .show()
}

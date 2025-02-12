package sparkPratice

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory
import org.apache.spark.sql.functions._

object SparkWordCount extends App {

  val spark = SparkSession.builder().appName("SparkWordCount").master("local[*]").getOrCreate()
  val logger = LoggerFactory.getLogger(this.getClass)
  logger.debug("Hi Lee, This is a debug message")
  logger.info("Hi Lee, This is an info message")
  logger.warn("Hi Lee, This is a warn message")
  logger.error("Hi Lee, This is an error message")

  spark.sparkContext.setJobDescription("Reading File")
  val filename = spark.read.textFile("data/logfiletext")
  //filename.persist()
  logger.info("No of partitions : "+filename.rdd.getNumPartitions)
  println("++++++++++++++++++++++++++++++++++++++ No of partitions :"+filename.rdd.getNumPartitions)

  //filename.foreach(print(_))
  // using RDD ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
  val wordslist = filename.rdd.flatMap(x => x.split(" "))
  // no stage or job display at UI screen, bcz action not called
  //println("++++++++++++++++++++++++++++++++++++++ Word List size :"+wordslist.collect().toList.length)
   // one job and one stage created with four tasks/partitions

  val wordcountlist = wordslist.map(x=>(x,1))
  println("No of partitions : "+wordcountlist.getNumPartitions)

  val finalwordcount = wordcountlist.reduceByKey(_+_)
  println("No of partitions : "+finalwordcount.getNumPartitions)
  finalwordcount.collect().foreach(println(_))

  val u1 = finalwordcount
  println("No of partitions after union U1 : "+u1.getNumPartitions)
  val u2 = finalwordcount
  println("No of partitions after union U2 : "+u2.getNumPartitions)
  
  val u3 = u1.union(u2)
  println("No of partitions after union U3 : "+u3.getNumPartitions)
  u3.foreach(println(_))

  val u4 = finalwordcount.repartition(3)
  println("No of partitions after union U1 : "+u1.getNumPartitions)
  println("No of partitions after union U4 : "+u4.getNumPartitions)
  val u5 = u1.union(u4)
  println("No of partitions after union U5 : "+u5.getNumPartitions)


  // using DataFrame ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
  /*filename.printSchema()
  import spark.implicits._
  val df1 = filename.select(explode(split(col("value"), "\\s+")).as("word"))
          .withColumn("cnt",lit(1))
          .groupBy("word").agg(sum("cnt").alias("wordcount"))
  df1.printSchema()
  df1.show(false)*/
  print("Press Any key to Exist")
  scala.io.StdIn.readLine()
  //filename.unpersist()
  spark.stop()
  println("SparkSession Stopped..!")
}

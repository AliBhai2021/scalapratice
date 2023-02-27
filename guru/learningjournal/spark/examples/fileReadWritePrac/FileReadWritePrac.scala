package guru.learningjournal.spark.examples.fileReadWritePrac

import org.apache.spark.sql.SparkSession

object FileReadWritePrac extends App{

  // Read File using Scala
  val data = scala.io.Source.fromFile("data/PartitionTest.csv")
  //data.foreach(println(_)) // prints character per line
  //data.getLines().foreach(println(_)) //print line per line

  val words = data.getLines().flatMap(_.split(",")).map(x=> (x,1))
  //data.getLines().flatMap(_.split(",")).foreach(println(_))
  //data.getLines().flatMap(_.split(",")).map(x=> (x,1)).foreach(println(_))
  val wordcount = words.toList.groupBy(_._1).mapValues(x=> x.map(_._2).sum)

  println("Scala count :")
  //wordcount.foreach(println(_))

  //Reading via RDD
  val spark = SparkSession.builder().appName("ReadWriteTest").master("local[2]").getOrCreate()
  val rddData = spark.sparkContext.textFile("data/PartitionTest.csv")
  val rddWords = rddData.flatMap(_.split(",")).map(x=>(x,1)).reduceByKey(_+_)
  println("Rdd words count :")
  println("Word Count RDD Partitions : "+rddWords.getNumPartitions) // based on local[*] value
  //rddWords.collect().foreach(println(_))

  //Reading via DataFrame
  import org.apache.spark.sql.functions._
  val dfData = spark.read.format("text").option("header","true").load("data/PartitionTest.csv")
    .withColumnRenamed("value","words")

        //using DataFrame Operation
        dfData.withColumn("words", split(col("words"),","))
          .withColumn("words", explode(col("words")))
          .withColumn("id",lit(1))
          .groupBy("words").agg(count("*"))
          .show(false)

      //using Spark-SQL
        dfData.createOrReplaceTempView("samplewordTable")
        spark.sql(""" select explode(split(words,",")) as word from samplewordTable """)
          .groupBy("word").agg(count("*"))
          .show(false)


}

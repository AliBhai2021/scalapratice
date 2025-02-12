package interviewquestions

import org.apache.spark.sql.SparkSession

object LTIMindTree extends App{

  val spark = SparkSession.builder().appName("wordCount").master("local[*]").getOrCreate()
  val inputFile = spark.read.textFile("data/wordcount.txt")
  inputFile.collect().foreach(println(_))
  println("::::::::::::::::::::::::::::wordcount1:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val wordcount1 = inputFile.rdd.flatMap(line=> line.split(" ")).map(x=> (x,1)).reduceByKey(_+_)
  wordcount1.collect().foreach(println(_))

  /*println("::::::::::::::::::::::::::::wordcount2:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val wordcount2 = inputFile.rdd.flatMap(line=> line.split(" ")).map(x=> (x,1)).groupBy(_._1)
    .mapValues(z => z.toList.map(_.))
  wordcount2.collect().foreach(println(_))*/

  println("::::::::::::::::::::::::::::wordcount3:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val wordcount3 = inputFile.rdd.flatMap(line=> line.split(" ")).map(x=> (x,1)).groupBy(_._1)
    .map(y=> (y._1, y._2.map(_._2).sum))
  wordcount3.collect().foreach(println(_))

  println("::::::::::::::::::::::::::::wordcount4:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val wordcount4 = inputFile.rdd.flatMap(line=> line.split(" ")).countByValue()
  wordcount4.foreach(println(_))
}

package guru.learningjournal.spark.examples.prac

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
object ReduceAndFoldWithSingleElementList extends Serializable with App {

  val spark = SparkSession.builder().appName("Conditions_prac").master("local[*]").getOrCreate()

  val rdd = spark.sparkContext.parallelize(List(1)) //(List.empty[Int])

  //actions => reduce, fold, aggregate, foreach
  println(rdd.reduce(_+_)) //not work with List.empty[Int]
  println(rdd.fold(0)((a,b)=>a+b)) //work with List.empty[Int]
  println(rdd.aggregate(0,0)((acc,value)=>(acc._1+value,acc._2+1),(acc1,acc2)=>(acc1._1+acc2._1,acc1._2+acc2._2)))

  //take(n) // no order follows
  //top(5) // gives orderwise top records if sort/order is defined

  //key pairs
  val pairdRdd = spark.sparkContext.parallelize(List(("panda",0),("pink",3),("pirate",3),("panda",1),("pink",4)))
  val computedValues = pairdRdd.mapValues(x => (x, 1)).reduceByKey((x, y) => {
    println("================= "+x._1+"  "+y._1+ " ::: "+x._2 + "  "+y._2)
    (x._1 + y._1, x._2 + y._2)})
  println("::::::::::::::"+computedValues.collect().mkString(" "))
}

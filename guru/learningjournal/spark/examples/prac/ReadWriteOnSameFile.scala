package guru.learningjournal.spark.examples.prac

import java.time.LocalDate
import java.util.Date

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Row, SparkSession, types}
import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._
object ReadWriteOnSameFile extends Serializable with App {

  val spark = SparkSession.builder().appName("ReadWriteOperations_Test").master("local[*]").getOrCreate()


  // Write Operation
  val fileData = spark.sparkContext.parallelize(List("ali","ahammed","darubhai","gari"))
  //println("=> "+fileData.collect.mkString(","))
  fileData.coalesce(2).saveAsTextFile("C:\\ALI\\JIRA\\Test\\newresult")
  /*
    // Read and write operation on same file
    val fileReadData = spark.sparkContext.textFile("C:\\ALI\\JIRA\\Test\\*.txt")
    val newData = fileReadData.map(x=> (x,x.length))
    println("==> "+newData.collect().mkString(";"))
    newData.saveAsTextFile("C:\\ALI\\JIRA\\Test\\newresult.txt")

    //Read operation
    val fileReadDataAgain = spark.sparkContext.textFile("C:\\ALI\\JIRA\\Test\\*.txt")
    println("=> "+fileReadDataAgain.collect.mkString(","))
  */

  val input = spark.sparkContext.wholeTextFiles("C:\\ALI\\JIRA\\Test\\newresult\\*")
  val result = input.mapValues{y=>
    println("y :"+y.toList)
    val nums = y.split(" ").map(x => x.length) //x.toDouble)
    println("nums :"+nums.mkString(" "))
    println("nums.sum :"+nums.sum)
    println("nums.size.toDouble :"+nums.size.toDouble)
    nums.sum / nums.size.toDouble
  }

  println("?????? "+result.collect().mkString("::::"))

}

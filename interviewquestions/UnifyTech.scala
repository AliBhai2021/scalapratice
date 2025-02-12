package interviewquestions

import org.apache.spark.sql.SparkSession

object UnifyTech extends App{
  // sort list and find words start with vowel with list position (index) from the given list

  val list = "apple is a sweet fruit in winter season only  "

  val sortedList = list.split("\\s+").zipWithIndex.sorted
  val filteredList = sortedList.filter(x=> x._1.toLowerCase().matches("^[aeiou].*"))
  println(filteredList.toList)
  filteredList.foreach{ case(w,i)=> println(s"$w  ${i+1}")}

  //======================================================================

  val spark = SparkSession.builder().appName("Lee").master("local[*]").getOrCreate()
  import spark.implicits._
  import org.apache.spark.sql.functions._

  val sampleData = List(("a","b","ab"),("a","b","ba"),("c","d","cd"),("c","d","dc"))
  val dataframe1 = sampleData.toDF("col1","col2","col3")
  dataframe1.show()

  //collect_list()
  val collectList = dataframe1.groupBy("col1","col2").agg(collect_list("col3").alias("list"))
  collectList.show()

  //explode()
  collectList.select(col("col1"),col("col2") ,explode(col("list")).alias("col3"))
   .show()

  // read json and print values likes below
  """{"empid":"101", "dept":["a","b","c"], "empid":"102", "dept":["a","d","f"]}"""
  """
    |empid  dept
    |101    a
    |101    b
    |101    c
    |102    a
    |102    d
    |103    e
    |""".stripMargin
  import org.json4s._
  import org.json4s.jackson.JsonMethods._

  case class Employee(empid: String, dept: List[String])
  val jsonString =
    """
      |[
      |  {"empid":"101", "dept":["a","b","c"]},
      |  {"empid":"102", "dept":["a","d","f"]}
      |]
      """.stripMargin

  implicit val formats: DefaultFormats.type = DefaultFormats
  val parsedJson = parse(jsonString)

  val employees = (parsedJson \ "empid").children.zip((parsedJson \ "dept").children)
    .map { case (empid, dept) =>
      Employee(empid.extract[String], dept.extract[List[String]])
    }

  println("| empid | dept |")
  employees.foreach { emp =>
    emp.dept.foreach { d =>
      println(s"| ${emp.empid} | $d |")
    }
  }


  // Create DataFrame from JSON string
  val df = spark.read
    .option("multiline", "true") // Handle multiline JSON string
    .json(Seq(jsonString).toDS)
  df.show()

  // Explode the array column to get each department in a separate row
  val explodedDF = df.withColumn("dept", explode($"dept"))

  // Show the result
  explodedDF.show()
}

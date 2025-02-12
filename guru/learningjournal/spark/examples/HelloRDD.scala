package guru.learningjournal.spark.examples

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object HelloRDD extends Serializable with App {

    @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

    //Create Spark Context
    val sparkAppConf = new SparkConf().setAppName("HelloRDD").setMaster("local[3]")
    val sparkContext = new SparkContext(sparkAppConf)
    val spark = SparkSession.builder()
      .appName("Hello DataSet")
      .master("local[3]")
      .getOrCreate()
    //Read your CSV file
    val linesRDD = sparkContext.textFile("data//sample.csv",2)

    //Give it a Structure and select only 4 columns
    case class SurveyRecord(Age: Int, Gender: String, Country: String, state: String)
    val colsRDD = linesRDD.map(line => {
      val cols = line.split(",").map(_.trim)
      SurveyRecord(cols(1).toInt, cols(2), cols(3), cols(4))
    })

    //Apply Filter
    val filteredRDD1 = colsRDD.filter(_.Gender == "Female")
    val filteredRDD2 = colsRDD.filter(_.Gender == "Male")

    //Manually implement the GroupBy
    val kvRDD = filteredRDD1.map(r => (r.Country, 1))
    val countRDD = kvRDD.reduceByKey((v1, v2) => v1 + v2)

    //Collect the result
    logger.info(">>>>>>>>>>>>>>>> "+ countRDD.collect().mkString(","))
    var result =countRDD.collect().mkString(",")
    result.foreach(println(_))
  //Manually implement the GroupBy
  val kvRDD2 = filteredRDD2.map(r => (r.Country, 1))
  val countRDD2 = kvRDD2.reduceByKey((v1, v2) => v1 + v2)
  //countRDD2.show()
  //Collect the result
  logger.info(">>>>>>>>>>>>>>>> "+ countRDD2.collect().mkString(","))

    //Stop the Spark context
    scala.io.StdIn.readLine()
    sparkContext.stop()


}

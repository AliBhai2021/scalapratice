package databricks

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}

object DataFrameCreation extends App{

  val spark = SparkSession.builder().master("local[*]").appName("CreateDF").getOrCreate()

  import spark.implicits._

  val rdd = spark.sparkContext.parallelize(List(1,2,3,4,5,6,7,8,9)).map(x=> Row(x))
  val schema = StructType(
    Seq(
      StructField("id",IntegerType, true)
    )
  )
  val df = spark.createDataFrame(rdd,schema)

  val listdata = List(("A",10),("A",20),("A",30),("B",10),("B",10),("C",10),("C",10),("D",10),("A",20)).toDF("id","marks")

  val listdata2 = listdata.select(sum("marks"))
  listdata2.show()
  listdata.groupBy("id").agg(avg("marks")).show()
}

package guru.learningjournal.spark.examples.prac

import org.apache.spark.sql.{Encoders, Row, SparkSession}
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object Ashraf_SparkSchemaFromDiffSources extends App{

  case class Name(fname:String,mname:String, lname:String)
  case class Employee(fullName:Name,age:Int,gender:String)
  val spark = SparkSession.builder()
    .master("local[3]")
    .appName("ashraf")
    .getOrCreate()

  val df_sql = Encoders.product[Employee].schema
  df_sql.printTreeString()
  val scala_df = ScalaReflection.schemaFor[Employee].dataType.asInstanceOf[StructType]
  scala_df.printTreeString()
    import spark.implicits._

    //df_sql.show(false)
 // sql_df.printTreeString()
  val data = Seq(("Ashraf"," ","Darubaigari",26,"M"),("rihana"," ","Darubaigari",25,"M"),
    ("Ali","Ahammed","Darubaigari",29,"M"))
  val df2 = data.toDF("fname","mname","lname","age","gender")
  println("ToDF Method")
  df2.printSchema();df2.show()
  val simpleData = Seq(
    Row(Row("Ashraf"," ","Darubaigari"),26,"M"),
    Row(Row("rihana"," ","Darubaigari"),25,"F"),
    Row(Row("Ali","Ahammed","Darubaigari"),29,"M")
  )

  val structschema = new StructType()
    .add("name",new StructType()
      .add("fname",StringType)
      .add("mname",StringType)
      .add("lname",StringType))
    .add("age",IntegerType)
    .add("gender",StringType)
  println("Struct Type Data Method")
  val df3 = spark.createDataFrame(spark.sparkContext.parallelize(simpleData),structschema)
  df3.show()
  println("Case class Schema using Scala Method")
  val df5 = spark.createDataFrame(spark.sparkContext.parallelize(simpleData),scala_df)
  df5.show()
  println("case class using Sql schema")
  val df6 = spark.createDataFrame(spark.sparkContext.parallelize(simpleData),df_sql)
  df6.show()
  val simpleData1 = Seq(
    Row("Ashraf"," ","Darubaigari",26,"M"),
    Row("rihana"," ","Darubaigari",25,"F"),
    Row("Ali","Ahammed","Darubaigari",29,"M")
  )
  val simpleSchema = StructType(Array(
    StructField("fname",StringType, true),
    StructField("mname",StringType,true),StructField("lname",StringType,true),
    StructField("age",IntegerType,true),
    StructField("gender",StringType,true)
  ))
  println("Schema Array")
  val df4 = spark.createDataFrame(spark.sparkContext.parallelize(simpleData1),simpleSchema)
  df4.show()
  val df7 = spark.createDataFrame(spark.sparkContext.parallelize(simpleData1),scala_df)
  df7.show()
  val df8 = spark.createDataFrame(spark.sparkContext.parallelize(simpleData1),df_sql)
  df8.show()
}

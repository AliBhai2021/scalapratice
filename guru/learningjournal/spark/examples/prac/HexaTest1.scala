package guru.learningjournal.spark.examples.prac

import org.apache.log4j.Logger
import org.apache.log4j.helpers.DateTimeDateFormat
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object HexaTest1 extends Serializable {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    //Create Spark Session
    val spark = SparkSession.builder()
      .appName("Hello DataSet")
      .master("local[3]")
      .getOrCreate()
//=====================================================================

/*
    //Exercise 1
    val list = List((101,"12,abc,def,ghi"),(102,"13,xyz,lmn"))
    val task1rdd = spark.createDataFrame(list)
    val task1_dataFrame= task1rdd.toDF("Id","Addr")
    val res1 = task1_dataFrame.withColumn("Addr",explode(split(col("Addr"),",")))
    res1.show
*/



    //Exercise 2
   // val dateFormatPattern = DateTimeFormat.
    val list2 = List(
      ("p1","10$","01-04-2021"), ("p2","15$","02-04-2021"),("p1","8$","03-04-2021"),
      ("p3","12$","05-04-2021"),("p2","10$","05-04-2021"),("p1","5$","10-04-2021"))
    val task2rdd = spark.createDataFrame(list2)
    val task2_dataFrame = task2rdd.toDF("productId","price","date")
    task2_dataFrame.show()


/*
    val res2 = task2_dataFrame.select(col("productId"),col("price"), to_date(col("date"),"dd-mm-yyyy").as("date"))
    res2.show
    val windowformat = Window.partitionBy("productId").orderBy("date")
    logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
    val res3 = res2.withColumn("endDate",lead("date",1, "2021-04-30").over(windowformat).minus(1))
    res3.show
    res3.orderBy("date").show
*/

/*    //Exercise #3
    val customerList = List(("c1","name1","p1"),("c2","name2","p1"),("c1","name1","p2"),("c3","name3","p1"))
    val productList = List(("p1","phone",300),("p2","battery",50))
    val customerRdd = spark.createDataFrame(customerList).toDF("id","name","prd_id")
    val productrdd = spark.createDataFrame(productList).toDF("pid","pname","price")

    val resJoin = customerRdd.join(productrdd, col("prd_id")===col("pid"))
    resJoin.show
    val window=Window.partitionBy("name")
    val res3 = resJoin.withColumn("total_products", count(col("name")).over(window))
        .withColumn("total_price", sum(col("price")).over(window))
    res3.show
    res3.select(col("id"),col("total_price"),col("total_products")).distinct().show
    */
    //Uncomment if you want to investigate SparkUI
    //scala.io.StdIn.readLine()

/*    //Exercise #4
     val test4 = Seq(("Feb1",27),("Feb2",30),("Feb3",28))
     val test4DataFrame = spark.createDataFrame(test4).toDF("date","temp")
    test4DataFrame.withColumn("nextDay", lead("temp",1).over(Window.partitionBy().orderBy("date")))
      //.where(abs(col("nextDay")-col("temp"))>=5)
      .where(abs(col("nextDay")-col("temp"))>=3)
      .show

    //Exercise #6
    test4DataFrame.withColumn("accumulation", sum("temp").over(Window.partitionBy().orderBy("date")))
        .show*/

/*    //Exercise #5
    val test5 = List((1,"cse"),(2,"ece"),(3,"cse"),(4,"ece"),(5,"eee"),(6,"cse"))
    val test5DataFrame = spark.createDataFrame(List((1,"cse"),(2,"ece"),(3,"cse"),(4,"ece"),(5,"eee"),(6,"cse"))).toDF("id","dept")
    test5DataFrame.withColumn("count",count(col("dept")).over(Window.partitionBy("dept")))
        .show

    val schema = StructType(Array(StructField("person_id",IntegerType,true), StructField("birth_date",StringType,true)))
    val schema2 = List(("person_id",IntegerType,true), ("birth_date",StringType,true))
    val dataa = List((1, "2013-01-30"),(2, "2012-01-01"))
    val sourceDF = spark.createDataFrame(dataa).toDF("person_id","birth_date")
      .withColumn( "birth_date", col("birth_date").cast("date"))
        .show
    import spark.implicits._

    val simpleData = Seq(("James", "Sales", 3000),
      ("Michael", "Sales", 4600),
      ("Robert", "Sales", 4100),
      ("Maria", "Finance", 3000),
      ("James", "Sales", 3000),
      ("Scott", "Finance", 3300),
      ("Jen", "Finance", 3900),
      ("Jeff", "Marketing", 3000),
      ("Kumar", "Marketing", 2000),
      ("Saif", "Sales", 4100)
    )
    val df = simpleData.toDF("employee_name", "department", "salary")
    df.show()
    */

    import spark.implicits._
    val daf1 = Seq((1,100),(2,200)).toDF("id","amount")
    val daf2 = Seq((3,300),(1,100)).toDF("id","amount")
    //val resdf1 = daf1.join(daf2, daf1("id") === daf2("id"),"inner")
    //resdf1.show()

    //resdf1.select(col("id"), col("amount")).show

    //resdf1.select(col("daf1.id"), col("daf2.amount")).show

    val unionData = daf1.join(daf2)
    unionData.show()
    //unionData.groupBy("id").show() //,(sum(col("amount"))).show()
    logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> End")

    def TransposeDF(df: DataFrame, columns: Seq[String], pivotCol: String): DataFrame = {
      val columnsValue = columns.map(x => "'" + x + "', " + x)
      println("columnsValue  "+columnsValue)
      val stackCols = columnsValue.mkString(",")
      println("stackCols     "+stackCols)
      val df_1 = df.selectExpr(pivotCol, "stack(" + columns.size + "," + stackCols + ")")
        .select(pivotCol, "col0", "col1")
      df.selectExpr(pivotCol, "stack(" + columns.size + "," + stackCols + ")").show()
      println("df_1           ");df_1.show()
      val final_df = df_1.groupBy(col("col0")).pivot(pivotCol)
                          .agg(concat_ws("", collect_list(col("col1"))))
        .withColumnRenamed("col0", pivotCol)
      println("final_df       "+final_df)
      final_df
    }

    val DataSeq = Seq(("Shirts", 10, 13, 34, 10), ("Trousers", 11, 2, 30, 20), ("Pants", 70, 43, 24, 60), ("Sweater", 101, 44, 54, 80))
    import spark.implicits._
    val productQtyDF = DataSeq.toDF("Products", "Small", "Medium", "Large", "ExLarge")
    println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
    println("productQtyDF")
    productQtyDF.show()
    val productTypeDF = TransposeDF(productQtyDF, Seq("Small", "Medium", "Large", "ExLarge"), "Products")
    println("TransposeDF .................")
    productTypeDF.show(false)

    /*    val test2 = List(("id",100,101,102),("name","san","krish","Ravi"),("sal",12000,11000,14000),("dept",10,20,20))
      .toDF("col1", "col2","col3","col4")
    test2.show()*/
      val test2 = List(("id","100","101","102"),("name","san","krish","Ravi"),("sal","12000","11000","14000"),("dept","10","20","20"))
                  .toDF("col1", "col2","col3","col4")
          test2.show()
    //val tt2 = test2.groupBy("col2","col3","col4").pivot("col1")


    spark.stop()
  }

}


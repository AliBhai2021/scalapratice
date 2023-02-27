package guru.learningjournal.spark.examples.prac

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql._


object SparkSql extends App {

  val spark = SparkSession.builder().appName("Ashraf").master("local[3]").getOrCreate()
  val data1 = Seq(("iphone", "Red"), ("S21", "Black"), ("iphone", "Green"), ("iphone", "Gold"), ("Note21", "Black")
    , ("Note21", "Gold"), ("S21", "Gold"), ("S21", "Silver"), ("S21", "Blue"), ("Note21", "Blue"), ("Note21", "Green"))

  import spark.implicits._

  /*val df = data1.toDF("Phone","Color")
  df.show()
  val temp = df.filter(df("Color") === "Black" || df("Color") ==="Gold")
  temp.show()
  df.createOrReplaceTempView("data")
  val temp1 = spark.sql("select * from data where Color IN ('Black', 'Gold')")
  temp1.show()*/
  val table1 = Seq(1, 0, 1, 2, 4)
  val table2 = Seq(1, 2, 3)
  val dft1 = table1.toDF("t1num")
  val dft2 = table2.toDF("t2num")
  dft1.createOrReplaceTempView("tab1")
  dft2.createOrReplaceTempView("tab2")
  val res = spark.sql("select * from tab1 t1 right JOIN tab2 t2 ON t1.t1num == t2.t2num ")
  //res.show(false)
  dft1.join(dft2, dft1("t1num") === dft2("t2num"), "right").show(false)
  dft1.join(dft2, dft1("t1num") === dft2("t2num"), "leftAnti").show(false)
  dft1.join(dft2, dft1("t1num") === dft2("t2num"), "left").show(false)
  dft1.join(dft2, dft1("t1num") === dft2("t2num"), "leftsemi").show(false)
  dft1.join(dft2, dft1("t1num") === dft2("t2num"), "inner").show(false)


  val ddata = Seq("abc/def/hij", "klm/nop/qrs/tvu","xyz/123")
  val ddatadf = ddata.toDF("col1")
  ddatadf.createOrReplaceTempView("test1")
  val res2 = spark.sql("select split(col1,'/') as ss from test1 ")
  res2.show(false)
  val res3 = spark.sql("select split(col1,'/')[-1] as ss from test1 ")
  res3.show(false)
  val res4 = spark.sql("select substring_index(col1,'/',-1) as ss from test1 ")
  res4.show(false)
  val res5 = spark.sql("select length(substring_index(col1,'/',-1)) as ss from test1 ")
  res5.show(false)
  val res6= spark.sql("select substring(col1,0,length(col1)-length(substring_index(col1,'/',-1))-1) as  from test1 ")
  res6.show(false)
  spark.close()
}
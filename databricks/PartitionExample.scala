package databricks

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType

object PartitionExample extends App{
  val spark = SparkSession.builder().master("local[3]").appName("Test").getOrCreate()
  val df = spark.read.option("header","true").csv("data/PartitionTest2.csv")
 // df.show(false)
  println("Partitions :" +df.rdd.getNumPartitions)
  //df.repartition(2).write.csv("data/PartitionTestOutput/PartitionTest1")  //Uses RoundRobinPartitioning
  //df.repartition(col("country")).write.csv("data/PartitionTestOutput/PartitionTest2") //Uses HashPartitioner
  //df.write.partitionBy("country").csv("data/PartitionTestOutput/PartitionTest3") //Use to write the data into sub-folder
  //df.repartition(2).write.partitionBy("country").csv("data/PartitionTestOutput/PartitionTest4")
  //df.repartition(col("country")).write.partitionBy("country").csv("data/PartitionTestOutput/PartitionTest5")

  //df.repartition(3,col("country")).write.csv("data/PartitionTestOutput/PartitionTest6")

  //df.write.bucketBy(5, "country").sortBy("emp_id")
    //.csv("data/PartitionTestOutput/PartitionTest8")
   // .saveAsTable("test_bucketed")

  df.coalesce(1).write.mode("overwrite").partitionBy("country")
  .csv("data/PartitionTestOutput/PartitionTestbucket")
/*

  val countDF = df.groupBy("country").count()
  println("Partitions :" +df.rdd.getNumPartitions)

  val desiredRowsPerPartition = 5
  val joinedDF = df.join(countDF, Seq("country"))
    .withColumn("my_secret_partition_key",  (rand(5) * col("count") / desiredRowsPerPartition)
      .cast(IntegerType)
    )
      .withColumn("randValue", rand(5))
  joinedDF.show()
  joinedDF.repartition(col("country"), col("my_secret_partition_key")).show()

  joinedDF
    .repartition(col("country"), col("my_secret_partition_key"))
    .drop("count", "my_secret_partition_key")
    .write
    .partitionBy("country")
    .csv("data/PartitionTestOutput/PartitionTest7")
*/


}

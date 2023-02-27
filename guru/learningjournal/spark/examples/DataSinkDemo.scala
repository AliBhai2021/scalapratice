package guru.learningjournal.spark.examples

import org.apache.log4j.Logger
import org.apache.spark.sql.{SaveMode, SparkSession}

object DataSinkDemo extends Serializable {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Spark Schema Demo")
      .master("local[3]")
      .getOrCreate()

    val flightTimeParquetDF = spark.read
      .format("parquet")
      .option("path", "dataSource/")
      .load()

    flightTimeParquetDF.printSchema()
    //Number of files?
    logger.info("Num Partitions before: " + flightTimeParquetDF.rdd.getNumPartitions)
    import org.apache.spark.sql.functions.spark_partition_id
    flightTimeParquetDF.groupBy(spark_partition_id()).count().show()
    val partitionedDF = flightTimeParquetDF.repartition(10)
    logger.info("Num Partitions after: " + partitionedDF.rdd.getNumPartitions)
    partitionedDF.groupBy(spark_partition_id()).count().show()

/*
    partitionedDF.write
      .format("avro")
      .mode(SaveMode.Overwrite)
      .option("path", "dataSink/avro/")
      .save()
*/

/*
    //Partition By?
    flightTimeParquetDF.write
      .format("json")
      .mode(SaveMode.Overwrite)
      .option("path", "dataSink/json/")
      .partitionBy( "OP_CARRIER", "ORIGIN")
      //.partitionBy( "OP_CARRIER")
      .option("maxRecordsPerFile", 10000)
      .save()
*/

    flightTimeParquetDF.write
      .format("csv")
      .mode(SaveMode.Overwrite)
      .partitionBy("OP_CARRIER")
      //.bucketBy(10,"ORIGIN")
      .option("path", "dataSink/csv/")
      //.option("maxRecordsPerFile", 10000)
      .save()

    logger.info("Finished Partitioning.")
    spark.stop()
  }

}

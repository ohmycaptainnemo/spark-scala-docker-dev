package com.example.spark

import org.apache.spark.sql.SparkSession

object App {
  def main(args: Array[String]): Unit = {
    
    // Initialize a local Spark Session
    val spark = SparkSession.builder()
      .appName("SparkDevEnvironment")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val data = Seq(
      ("Alice", "Engineering", 120000),
      ("Bob", "Marketing", 95000),
      ("Charlie", "Engineering", 130000)
    )
    
    val df = data.toDF("Name", "Department", "Salary")
    
    println("--- Executing Spark Action ---")
    df.groupBy("Department").avg("Salary").show()

    spark.stop()
  }
}
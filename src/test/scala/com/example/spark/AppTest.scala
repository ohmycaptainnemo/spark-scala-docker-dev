package com.example.spark

import org.apache.spark.sql.SparkSession
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfterAll

class AppTest extends AnyFunSuite with BeforeAndAfterAll {

  @transient var spark: SparkSession = _

  override def beforeAll(): Unit = {
    spark = SparkSession
      .builder()
      .appName("SparkUnitTests")
      .master("local[2]")
      .getOrCreate()
  }

  override def afterAll(): Unit = {
    if (spark != null) {
      spark.stop()
    }
  }

  test("DataFrame should correctly initialize and count rows") {
    // 1. Assign the 'var' to a local, stable 'val'
    val localSpark = spark

    // 2. Import implicits from the stable local variable
    import localSpark.implicits._

    val testData = Seq(("Test1", 1), ("Test2", 2)).toDF("Name", "Value")

    assert(testData.count() == 2)
    assert(testData.columns.contains("Name"))
  }
}

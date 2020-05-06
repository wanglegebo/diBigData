package com.dianjue.utils
import org.apache.spark
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @description: ${description}
  * @author: Wang Bo
  * @create: 2020-05-06 12:52
  **/
object SparkUtil {
  def getSparkSession(): SparkSession ={
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[2]")
      .appName("test")
      .enableHiveSupport()
      .getOrCreate()
    spark
  }


}

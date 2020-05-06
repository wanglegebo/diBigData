package com.dianjue.App

import com.dianjue.utils.SparkUtil
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @description: 测试
  * @author: Wang Bo
  * @create: 2020-05-06 13:47
  **/
object DisplayApp {
  def main(args: Array[String]): Unit = {
    val session: SparkSession = SparkUtil.getSparkSession()
    val sql = "select count(*) from dwd_display_log"
    session.sql("use ctm")
    val dataFrame: DataFrame = session.sql("show tables")
    dataFrame.show()
    session.stop()
  }
}

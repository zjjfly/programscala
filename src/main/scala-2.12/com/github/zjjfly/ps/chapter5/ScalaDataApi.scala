package com.github.zjjfly.ps.chapter5

import com.github.zjjfly.ps.chapter5.database_api.javadb.JRow

/**
  * Created by zjjfly on 2017/1/29.
  */
package scaladb {

  object implicits {

    implicit class SRow(jRow: JRow) {
      def get[T](colName: String)(implicit toT: (JRow, String) => T): T =
        toT(jRow, colName)
    }
    //下面的三个隐式值限定了get[T]方法的T只能是Int,Double和String
    implicit val jrowToInt: (JRow, String) => Int =
      (jrow: JRow, colName: String) => jrow.getInt(colName)
    implicit val jrowToDouble: (JRow, String) => Double =
      (jrow: JRow, colName: String) => jrow.getDouble(colName)
    implicit val jrowToString: (JRow, String) => String =
      (jrow: JRow, colName: String) => jrow.getText(colName)
  }

}

object DB {

  import com.github.zjjfly.ps.chapter5.scaladb.implicits._

  def main(args: Array[String]): Unit = {
    val row = JRow("one" -> 1, "two" -> 2.2, "three" -> "THREE!")
    val oneValue1: Int = row.get("one")
    val twoValue1: Double = row.get("two")
    val threeValue1: String = row.get("three")
    println(s"one1 -> $oneValue1")
    println(s"two1 -> $twoValue1")
    println(s"three1 -> $threeValue1")
    val oneValue2 = row.get[Int]("one")
    val twoValue2 = row.get[Double]("two")
    val threeValue2 = row.get[String]("three")
    println(s"one2 -> $oneValue2")
    println(s"two2 -> $twoValue2")
    println(s"three2 -> $threeValue2")
  }
}

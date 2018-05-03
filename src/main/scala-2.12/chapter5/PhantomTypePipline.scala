package chapter5

import scala.collection.immutable.{List, Map}

/**
  * Created by zjjfly on 2017/3/13.
  */
object Pipline {

  implicit class toPiped[V](value: V) {
    def |>[R](f: V => R) = f(value)

  }

}

object CalculatePayroll2 {
  def main(args: Array[String]): Unit = {
    import Pipline._
    import Payroll._

    val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)
    val pay = start(e) |>
      minus401k |>
      minusInsurance |>
      minusTax |>
      minusFinalDeductions
    val twoWeekGross = e.annualSalary / 26.0F
    val twoWeekNet = pay.netPay
    val percent = (twoWeekNet / twoWeekGross) * 100
    println(s"For ${e.name},the gross vs. net pay every 2 week is:")
    println(
      f"  $$${twoWeekGross}%.2f vs.$$${twoWeekNet}%.2f or ${percent}%.1f%%"
    )
    val (a, b) = (1, 2)
  }
}

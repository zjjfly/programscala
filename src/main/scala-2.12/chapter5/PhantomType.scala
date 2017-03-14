package chapter5

import javafx.scene.effect.FloatMap

/**
  * Created by zjjfly on 2017/3/13.
  */
sealed trait PreTaxDeductions

sealed trait PostTaxDeductions

sealed trait Final

case class Employee(
                     name: String,
                     annualSalary: Float,
                     taxRate: Float,
                     insurancePremiumPerPayPeriod: Float,
                     _401DeductionRate: Float,
                     postTaxDeductions: Float
                   )

case class Pay[Step](employee: Employee, netPay: Float)

object Payroll {

  def start(employee: Employee): Pay[PreTaxDeductions] = Pay[PreTaxDeductions](employee, employee.annualSalary / 26.0F)

  def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.insurancePremiumPerPayPeriod
    pay copy (netPay = newNet)
  }


  def minus401k(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee._401DeductionRate * pay.netPay
    pay copy (netPay = newNet)
  }

  def minusTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] = {
    val newNet = pay.netPay - (pay.employee.taxRate * pay.netPay)
    pay copy (netPay = newNet)
  }

  def minusFinalDeductions(pay: Pay[PostTaxDeductions]): Pay[Final] = {
    val newNet = pay.netPay - pay.employee.postTaxDeductions
    pay copy (netPay = newNet)
  }
}

object CalculatePayroll{
  def main(args: Array[String]): Unit = {
    val e = Employee("Buck Trends",100000.0F,0.25F,200F,0.10F,0.05F)
    val pay1 = Payroll start e
    val pay2 = Payroll minus401k pay1
    val pay3 = Payroll minusInsurance pay2
    val pay = Payroll minusTax pay3
    val twoWeekGross = e.annualSalary/26.0F
    val twoWeekNet = pay.netPay
    val percent = (twoWeekNet/twoWeekGross)*100
    println(s"For ${e.name},the gross vs. net pay every 2 week is:")
    println(
      f"  $$${twoWeekGross}%.2f vs.$$${twoWeekNet}%.2f or ${percent}%.1f%%"
    )
  }
}
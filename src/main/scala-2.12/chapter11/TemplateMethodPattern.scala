package chapter11

/**
  * Created by zjjfly on 2017/4/21.
  */
object TemplateMethodPattern extends App{
  case class Address(city: String, state: String, zip: String)
  case class Employee(name: String, salary: Double, address: Address)
  abstract class Payroll{
    def netPay(employee: Employee):Double={
      val fexTaxes=calcFedTaxes(employee.salary)
      val statTaxes=calcStateTaxes(employee.salary,employee.address)
      employee.salary-fexTaxes-statTaxes
    }
    def calcFedTaxes(salary: Double): Double
    def calcStateTaxes(salary: Double, address: Address): Double
  }

  object Payroll2014 extends Payroll {
    val stateRate=Map(
      "XX" -> 0.05,
      "YY" -> 0.03,
      "ZZ" -> 0.0
    )

    override def calcFedTaxes(salary: Double): Double = salary*0.25

    override def calcStateTaxes(salary: Double, address: Address): Double = salary*stateRate(address.state)
  }
  val tom = Employee("Tom Jones", 100000.0, Address("MyTown", "XX", "12345"))
  val jane = Employee("Jane Doe", 110000.0, Address("BigCity", "YY", "67890"))
  assert(Payroll2014.netPay(tom) == 70000)
  assert(Payroll2014.netPay(jane) == 79200)
}
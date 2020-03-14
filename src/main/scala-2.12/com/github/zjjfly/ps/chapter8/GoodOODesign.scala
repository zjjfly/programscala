package com.github.zjjfly.ps.chapter8

import com.github.zjjfly.ps.chapter8.Constructor.Address

/**
  * Created by zjjfly on 2017/4/15.
  */
object GoodOODesign extends App {

  //当使用子类继承似乎是正确的方法时，考虑把行为分离到trait，然后在类中混入这些trait
  trait PersonState { // val name: String
    val age: Option[Int]
    val address: Option[Address]
  }

  case class Person(name: String,
                    age: Option[Int] = None,
                    address: Option[Address] = None)
      extends PersonState

  trait EmployeeState {
    val title: String
    val manager: Option[Employee]
  }

  case class Employee(name: String,
                      age: Option[Int] = None,
                      address: Option[Address] = None,
                      title: String = "[unknown]",
                      manager: Option[Employee] = None)
      extends PersonState
      with EmployeeState

}

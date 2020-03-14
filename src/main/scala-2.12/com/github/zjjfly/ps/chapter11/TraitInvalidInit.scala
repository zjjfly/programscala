package com.github.zjjfly.ps.chapter11

/**
  * Created by zjjfly on 2017/4/24.
  */
object TraitInvalidInit extends App {

  trait AbstractT {
    println("In AbstractT:")
    val value: Int
    val inverse = 1.0 / value
    println("AbstractT: value = " + value + ", inverse = " + inverse)
  }

  val obj = new AbstractT {
    println("In obj:")
    val value: Int = 10
  }
  println("obj.value = " + obj.value + ", inverse = " + obj.inverse)

  trait AbstractT1 {
    println("In AbstractT1:")
    val value: Int
    lazy val inverse = 1.0 / value
    //    println("AbstractT1: value = "+value+", inverse = "+inverse)
  }

  val obj1 = new AbstractT1 {
    println("In obj1:")
    val value: Int = 10
  }
  println("obj1.value = " + obj1.value + ", inverse = " + obj1.inverse)

  trait AbstractT2 {
    println("In AbstractT2:")
    val value: Int
    val inverse = 1.0 / value
    println("AbstractT2: value = " + value + ", inverse = " + inverse)
  }

  val obj2 = new {
    val value = 10
  } with AbstractT2 {
    println("In obj2:")
  }
  println("obj2.value = " + obj2.value + ", inverse = " + obj2.inverse)

}

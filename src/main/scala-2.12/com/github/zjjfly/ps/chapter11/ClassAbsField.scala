package com.github.zjjfly.ps.chapter11

/**
  * Created by zjjfly on 2017/4/25.
  */
object ClassAbsField extends App {
  abstract class AbstractC1 {
    val name: String
    var count: Int
  }
  class ClassWithAbstractC1 extends AbstractC1 {
    val name = "ClassWithAbstractC1"
    var count = 1
  }
  val c = new ClassWithAbstractC1()
  println(c.name)
  println(c.count)
}

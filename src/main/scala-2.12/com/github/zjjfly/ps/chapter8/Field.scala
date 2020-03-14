package com.github.zjjfly.ps.chapter8

/**
  * Created by zjjfly on 2017/4/14.
  */
object Field extends App {

  class Name(s: String) {
    private var _value: String = s
    def value: String = _value
    def value_=(newValue: String): Unit = _value = newValue
  }

  private val name = new Name("jjzi")
  name.value = "ass"//当编译器看到value_=这样名字的方法时，调用的时候可以省略掉_
  assert(name.value=="ass")

}

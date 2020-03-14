package com.github.zjjfly.ps.chapter8

/**
  * Created by zjjfly on 2017/4/14.
  */
object ClassObject extends App {

  //  trait Foo {
  //    val x: Int
  //
  //    def x: Int
  //  }
  //方法和字段只有当方法有参数列表的时候才可以同名
  trait Foo {
    val x: Int

    def x(i: Int): Int
  }

}

package com.github.zjjfly.ps.chapter13

/**
  * Created by zjjfly on 2017/5/24.
  */
object CompoundType extends App {
  trait T1
  trait T2
  class C
  val c = new C with T1 with T2
  type B = T1 with T2
}

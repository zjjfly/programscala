package com.github.zjjfly.ps.chapter24

/**
  * Created by zjjfly on 2017/6/5.
  */

import scala.reflect.runtime.universe._

object FunctionVariance extends App {

  class CSuper {
    def msuper() = println("CSuper")
  }

  class C extends CSuper {
    def m() = println("C")
  }

  class CSub extends C {
    def msub() = println("CSub")
  }

  println(typeOf[C => C] =:= typeOf[C => C])
  println(typeOf[CSuper => CSub] =:= typeOf[C => C])
  println(typeOf[CSub => CSuper] =:= typeOf[C => C])
  println(typeOf[C => C] <:< typeOf[C => C])
  println(typeOf[CSuper => CSub] <:< typeOf[C => C])
  println(typeOf[CSub => CSuper] <:< typeOf[C => C])
}

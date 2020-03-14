package com.github.zjjfly.ps.chapter6

/**
  * Created by zjjfly on 2017/3/23.
  */
object Currying extends App {

  def cat1(s1: String)(s2: String): String = s1 + s2

  def cat2(s1: String): (String) => String = (s2: String) => s1 + s2

  assert(cat1("1")("2") == cat2("1")("2"))

  def cat3(s1: String, s2: String): String = s1 + s2

  private val cat3Curried = (cat3 _).curried //curried函数可以把一个多参数的函数转成curried形式,_号还是为了消除歧义

  assert(cat3Curried("1")("2") == cat2("1")("2"))

  private val uncurried = Function.uncurried(cat3Curried)

  assert(uncurried("1", "2") == cat3Curried("1")("2"))

  //currying的应用场景
  def multiplier(i: Int)(factor: Int): Int = i * factor

  val byFive = multiplier(5) _

  val byTen = multiplier(10) _

}

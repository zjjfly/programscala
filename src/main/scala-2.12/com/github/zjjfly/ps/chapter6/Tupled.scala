package com.github.zjjfly.ps.chapter6

/**
  * Created by zjjfly on 2017/3/24.
  */
object Tupled extends App {

  val d3 = (2.2, 3.3, 4.4)

  def mult(d1: Double, d2: Double, d3: Double): Double = d1 * d2 * d3

  val multTupled = Function.tupled(mult _) //为了消除歧义，要加_

  println(multTupled(d3))

  private val multUntupled = Function.untupled(multTupled)

  println(multUntupled(d3._1, d3._2, d3._3))
}

package com.github.zjjfly.ps.chapter7

import scala.util.{Failure, Success, Try}

/**
  * Created by zjjfly on 2017/4/7.
  */
object Trys extends App {
  def positive(i: Int): Try[Int] = Try {
    assert(i > 0, s"nonpositive number $i")
    i
  }

  val result1: Try[Int] = for {
    i1 <- positive(5)
    i2 <- positive(10 * i1)
    i3 <- positive(25 * i2)
    i4 <- positive(2 * i3)
  } yield i1 + i2 + i3 + i4
  assert(result1 == Success(3805))

  val result2: Try[Int] = for {
    i1 <- positive(5)
    i2 <- positive(-1 * i1)
    i3 <- positive(25 * i2)
    i4 <- positive(-2 * i3)
  } yield i1 + i2 + i3 + i4
  assert(result2.isInstanceOf[Failure[Int]])

}

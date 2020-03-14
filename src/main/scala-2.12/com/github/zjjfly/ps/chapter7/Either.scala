package com.github.zjjfly.ps.chapter7

import scala.util.Either.{LeftProjection, RightProjection}

/**
  * Created by zjjfly on 2017/4/5.
  */
object Either extends App {
  def positive(i: Int): Either[String, Int] =
    if (i > 0) Right(i) else Left(s"negative number $i")

  val result = for {
    i1 <- positive(5).right
    i2 <- positive(10 * i1).right
    i3 <- positive(25 * i2).right
    i4 <- positive(2 * i3).right
  } yield i1 + i2 + i3 + i4
  assert(result == Right(3805))

  val l: Either[String, Int] = Left("boo")
  val r: Either[String, Int] = Right(12)
  //使用类型的中缀表达式，使得类型更易读
  type Or[A, B] = Either[A, B]
  val l1: String Or Int = Left("better?")

  //Either的right和left方法返回的是LeftProjection和RightProjection，这两个类实现了使用for推导式所需的方法
  assert(l.left.isInstanceOf[LeftProjection[String, Int]])
  assert(r.right.isInstanceOf[RightProjection[String, Int]])
  //如果LeftProjection实际是Right转变来的，那么调用foreach和map这样的方法不会产生作用,RightProjection也是
  assert(l.right.map(_.toDouble) == l)
  assert(r.left.map(_.toUpperCase) == r)

  //Either是抛出异常的一种替代方案，使我们保持对调用栈的控制
  //它还可以使得用户自己的API的行为更加明显,通过方法签名就可以看出
  def addInt(s1: String, s2: String): Either[NumberFormatException, Int] = {
    try {
      Right(s1.toInt + s2.toInt)
    } catch {
      case nfe: NumberFormatException => Left(nfe)
    }
  }
}

package com.github.zjjfly.ps.chapter7

/**
  * Created by zjjfly on 2017/3/31.
  */
object for_xxx extends App {
  val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

  //for和foreach对比
  //从对比可以看出，不带yield的生成器表达式相当于调用foreach函数
  for (elem <- states) {
    println(elem)
  }
  states.foreach(println)

  //带yield的for，相当于调用map函数
  val states1 = for (elem <- states) yield elem.toUpperCase
  assert((states map (_.toUpperCase)) == states1)

  //带多个生成器的for语句，相当于调用flatMap函数
  val states2 = for {
    elem <- states
    c <- elem
  } yield s"$c-${c.toUpper}"
  assert(states2 == states.flatMap(_.toSeq map (c => s"$c-${c.toUpper}")))

  //带有guard的for语句,相当于调用withFilter语句，如果这个集合没有withFilter方法，那么使用filter方法。
  val states3 = for {
    elem <- states
    c <- elem
    if c.isLower
  } yield s"$c-${c.toUpper}"
  assert(states3 == states.flatMap(_.toSeq withFilter (_.isLower) map (c =>
    s"$c-${c.toUpper}")))

  for {
    s <- states
    c <- s
    if c.isLower
    c2 = s"$c-${c.toUpper} "
  } yield c2

  states flatMap (_.toSeq withFilter (_.isLower) map { c =>
    val c2 = s"$c-${c.toUpper} "
    c2
  })
}

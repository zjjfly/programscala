package com.github.zjjfly.ps.chapter6

/**
  * Created by zjjfly on 2017/3/27.
  */
object Mapping extends App {
  val intToString = (i: Int) => s"N=$i"
  val flist = Combinator.map(intToString) _
  val list = flist(List(1, 2, 3, 4))
  assert(List("N=1", "N=2", "N=3", "N=4") == list)
}
object Combinator {
  def map[A, B](f: (A) ⇒ B)(list: List[A]): List[B] = list map f
}

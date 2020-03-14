package com.github.zjjfly.ps.Chapter3

import scala.annotation.tailrec

/**
  * Created by zjjfly on 2016/11/20.
  */
object CallByName {
  def main(args: Array[String]): Unit = {
    var count = 0
    continue(count < 5) {
      println(s"at $count")
      count += 1
    }
  }
  //by-name参数的一个重要特性是:每当方法中引用它的时候都会重新解析一次
  @tailrec
  def continue(conditional: => Boolean)(body: => Unit) {
    if (conditional) {
      body
      continue(conditional)(body)
    }
  }
}

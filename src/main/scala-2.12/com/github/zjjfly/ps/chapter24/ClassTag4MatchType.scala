package com.github.zjjfly.ps.chapter24

import scala.reflect.ClassTag

/**
  * Created by zjjfly on 2017/6/5.
  */
object ClassTag4MatchType extends App {
  def useClassTag[T: ClassTag](seq: Seq[T]) = seq match {
    case Nil => "Nothing"
    case head +: _ => implicitly(head).getClass.toString //implicitly已经得到了一个ClassTag对象，后面的(head)是调用它的apply方法。
  }

  def check(seq: Seq[_]) = s"Seq: ${useClassTag(seq)}"

  Seq(Seq(5.5, 5.6, 5.7), Seq("a", "b"), Seq(1, "two", 3.14), Nil) foreach {
    case seq: Seq[_] => println("%20s: %s".format(seq, check(seq)))
    case x => println("%20s: %s".format(x, "unknown!"))
  }
}

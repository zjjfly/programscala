package chapter17

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
  * Created by zjjfly on 2017/6/2.
  */
object Futures extends App {
  val futures = (0 to 9) map { i =>
    Future {
      val s = i.toString
      println(s)
      s
    }
  }
  private val f = Future.reduceLeft(futures)((s1, s2) => s1 + s2)
  val n = Await.result(f, Duration.Inf)
  println(n)
}

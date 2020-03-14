package com.github.zjjfly.ps.chapter17

/**
  * Created by zjjfly on 2017/6/2.
  */
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object FutureCallBack extends App {
  val pool = Executors.newCachedThreadPool()
  implicit val execContext = ExecutionContext.fromExecutor(pool)
  val doComplete = (t: Try[String]) =>
    t match {
      case s @ Success(_) => println(s)
      case f @ Failure(_) => println(f)
  }
  val futures: Seq[Future[String]] = (0 to 9) map {
    case i if i % 2 == 0 => Future.successful(i toString)
    case i               => Future.failed(`That'sOdd`(i))
  }
  futures foreach (_ onComplete doComplete)
  pool.shutdown();
}

case class `That'sOdd`(i: Int) extends RuntimeException(s"odd $i receive!")

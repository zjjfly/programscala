package chapter15

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by zjjfly on 2017/5/26.
  */
object DepMethodType extends App {
  MyService.handle(LocalComputation(Future(LocalResponse(1))))
  MyService.handle(RemoteComputation(Future(RemoteResponse("Remote call"))))
}

case class LocalResponse(statusCode: Int)

case class RemoteResponse(message: String)

sealed trait Computation {
  type Response

  def work: Future[Response]
}

case class LocalComputation(work: Future[LocalResponse]) extends Computation {
  override type Response = LocalResponse
}

case class RemoteComputation(work: Future[RemoteResponse]) extends Computation {
  override type Response = RemoteResponse
}

object MyService {
  def handle(computation: Computation): computation.Response = {
    val duration = Duration(5, TimeUnit.SECONDS)
    Await.result(computation.work, duration)
  }
}
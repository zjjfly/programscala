package chapter17

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}
import scala.util.control.NonFatal
import akka.pattern.ask

import scala.collection.immutable
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by zjjfly on 2017/6/3.
  */
class ServerActor extends Actor with ActorLogging {

  import Message._

  implicit val timeout = Timeout(1.seconds)

  override def receive: Receive = initial

  override def supervisorStrategy: SupervisorStrategy = {
    val decider: SupervisorStrategy.Decider = {
      case WorkerActor.CrashException => SupervisorStrategy.restart
      case NonFatal(_) => SupervisorStrategy.Resume
    }
    OneForOneStrategy() {
                          decider orElse super.supervisorStrategy.decider
                        }
  }

  var workers: immutable.Seq[ActorRef] = Vector.empty[ActorRef]

  val initial: Receive = {
    case Start(numberOfWorkers) =>
      workers = ((1 to numberOfWorkers) map makeWorker).toVector
      context become processRequest
  }
  val processRequest: Receive = {
    case c@Crash(n) => workers(n % workers.size) ! c
    case DumpAll =>
      val futures= workers.map(_ ? DumpAll)
      Future.foldLeft(futures)(Vector.empty[Any])(_ :+ _).onComplete(askHandler(s"State of workers"))
    case Dump(n) =>
      (workers(n % workers.size) ? DumpAll).map(Vector(_)).onComplete(askHandler(s"State of worker $n"))
    case request: Request =>
      val key = request.key.toInt
      val index = key % workers.size
      workers(index) ! request
    case Response(Success(message)) => printResult(message)
    case Response(Failure(ex)) => printResult(s"ERROR! ex")
  }

  def askHandler(prefix: String): PartialFunction[Try[Any], Unit] = {
    case Success(suc) => suc match {
      case vect: Vector[_] =>
        printResult(s"prefix:\n")
        vect foreach {
          case Response(Success(message)) =>
            printResult(s"$message")
          case Response(Failure(ex)) =>
            printResult(s"ERROR! Success received wrapping $ex")
        }
      case _ => printResult(s"BUG! Expected a vector,got $suc")
    }
    case Failure(ex) => printResult(s"ERROR! $ex")
  }

  protected def printResult(message: String) = {
    println(s"<< $message")
  }

  protected def makeWorker(i: Int): ActorRef =
    context.actorOf(Props[WorkerActor], s"worker-$i")
}

object ServerActor {
  def make(system: ActorSystem): ActorRef = {
    system.actorOf(Props[ServerActor], "server")
  }
}

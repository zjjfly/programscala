package com.github.zjjfly.ps.chapter17

import akka.actor.{Actor, ActorLogging}
import com.github.zjjfly.ps.chapter17.Message._

import scala.util.{Success, Try}

/**
  * Created by zjjfly on 2017/6/3.
  */
class WorkerActor extends Actor with ActorLogging {
  private val datastore = collection.mutable.Map.empty[Long, String]
  override def receive: Receive = {
    case Create(key, value) =>
      datastore += key -> value
      sender ! Response(Success(s"$key->$value added"))
    case Read(key) =>
      sender ! Response(Try(s"${datastore(key)} found for key = $key"))
    case Update(key, value) =>
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value updated"))
    case Delete(key) =>
      datastore -= key
      sender ! Response(Success(s"$key deleted"))
    case Crash(_) => throw WorkerActor.CrashException
    case DumpAll =>
      sender ! Response(Success(s"${self.path}: datastore = $datastore"))
  }
}
object WorkerActor {
  case object CrashException extends RuntimeException("Crash!")
}

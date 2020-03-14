package com.github.zjjfly.ps.Chapter1

import com.github.zjjfly.ps.Chapter1.Messages.{Exit, Finished, Response}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created by zjjfly on 16/8/1.
  */
private object Start
object ShapesActorDriver {
  def main(args: Array[String]) {
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(Props(new ShapesDrawingActor), "drawingActor")
    val driver =
      system.actorOf(Props(new ShapesActorDriver(drawer)), "drawingService")
    driver ! Start
  }
}
class ShapesActorDriver(drawActor: ActorRef) extends Actor {
  override def receive: Receive = {
    case Start =>
      drawActor ! Circle(Point(0.0, 0.0), 1.0)
      drawActor ! Rectangle(Point(0.0, 0.0), 2, 5)
      drawActor ! 3.14159
      drawActor ! Triangle(Point(0.0, 0.0), Point(2.0, 0.0), Point(1.0, 2.0))
      drawActor ! Exit
    case Finished =>
      println(s"ShapesActorDriver:clean up...")
      context.system.terminate
    case re: Response =>
      println("ShapesActorDriver:Response=" + re)
    case unexpected =>
      println(
        "ShapesActorDriver: ERROR:Received an unexpected message = " + unexpected)
  }
}

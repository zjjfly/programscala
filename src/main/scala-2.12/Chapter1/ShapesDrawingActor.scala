package Chapter1

/**
  * Created by zjjfly on 16/8/1.
  */
object Messages {
  object Exit
  object Finished
  case class Response(message: String)
}
import akka.actor.Actor
class ShapesDrawingActor extends Actor {
  import Messages._
  override def receive: Receive = {
    case s: Shape =>
      s.draw(str => println(s"ShapesDrawingActor:$str"))
      sender ! Response(s"ShapesDrawingActor:$s drawn")
    case Exit =>
      println(s"ShapesDrawingActor:exiting...")
      sender ! Finished
    case unexpected =>
      val response = Response(s"ERROR:Unkown message: $unexpected")
      println(s"ShapesDrawingActor:$response")
      sender ! response
  }
}

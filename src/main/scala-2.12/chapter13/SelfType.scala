package chapter13

/**
  * Created by zjjfly on 2017/5/23.
  */
object SelfType extends App {

  trait Persistence {
    def startPersistence(): Unit
  }

  trait Midtier {
    def startMidtier(): Unit
  }

  trait UI {
    def startUI(): Unit
  }

  trait Database extends Persistence {
    def startPersistence(): Unit = println("Starting Database")
  }

  trait BizLogic extends Midtier {
    def startMidtier(): Unit = println("Starting BizLogic")
  }

  trait WebUI extends UI {
    def startUI(): Unit = println("Starting WebUI")
  }

  trait App {
    self: Persistence with Midtier with UI =>
    def run(): Unit = {
      startPersistence()
      startMidtier()
      startUI()
    }
  }

  object MyApp extends App with Database with BizLogic with WebUI //
  MyApp.run()

  val c1 = new C1
  c1.talk("Hello")
  c1.c2.c3.talk("World")
}

class C1 {
  outer =>
  def talk(message: String): Unit = println("C1.talk: " + message)

  class C2 {

    class C3 {
      def talk(message: String): Unit = outer.talk("C3.talk: " + message) //
    }

    val c3 = new C3
  }

  val c2 = new C2
}

package chapter13

/**
  * Created by zjjfly on 2017/5/24.
  */
object StructuralType extends App {

  trait Subject {

    import scala.language.reflectiveCalls

    type State
    type Observer = {def receiveUpdate(state: Any): Unit}
    private var observers: List[Observer] = Nil

    def addObserver(observer: Observer): Unit = observers ::= observer

    def notifyObservers(state: State): Unit = observers foreach (_.receiveUpdate(state))
  }
  val subject = new Subject {

    type State=Int

    protected var count=0

    def increment():Unit={

      count += 1

      notifyObservers(count)
    }

  }


  trait SubjectFunc {
    type State
    type Observer = State => Int
    private var observers: List[Observer] = Nil

    def addObserver(observer: Observer): Unit = observers ::= observer

    def notifyObservers(state: State): Unit = observers foreach (_.apply(state))
  }

}

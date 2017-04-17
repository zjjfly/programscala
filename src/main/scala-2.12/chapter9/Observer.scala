package chapter9
package ui1

import chapter9.ui.Widget

/**
  * Created by zjjfly on 2017/4/15.
  */
object Observer extends App {

  trait Observer[-State] {
    def receiveUpdate(state: State): Unit
  }

  trait Subject[State] {
    private var observers: List[Observer[State]] = Nil

    def addObserver(observer: Observer[State]): Unit =
      observers ::= observer

    def notifyObservers(state: State) =
      observers.foreach(o => o.receiveUpdate(state))
  }

  class Button(val label: String) extends Widget {
    def click(): Unit = updateUI()

    def updateUI(): Unit = {
      /* logic to change GUI appearance */
    }
  }

  class ObservableButton(name: String) extends Button(name) with Subject[Button] {
    override def click(): Unit = {
      super.click()
      notifyObservers(this)
    }
  }

  class ButtonCountObserver extends Observer[Button] {
    var count = 0

    override def receiveUpdate(state: Button): Unit = {
      count += 1
    }

  }

  private val button = new ObservableButton("btn")
  private val bco1 = new ButtonCountObserver
  private val bco2 = new ButtonCountObserver
  button.addObserver(bco1)
  button.addObserver(bco2)
  (1 to 5) foreach (_ => button.click())
  assert(bco1.count==5)
  assert(bco2.count==5)

}

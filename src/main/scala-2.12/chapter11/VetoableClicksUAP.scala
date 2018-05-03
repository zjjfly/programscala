package chapter11

import chapter9.ui1.Observer.Observer
import chapter9.ui2.{Button, Clickable, ObservableClicks}

/**
  * Created by zjjfly on 2017/4/25.
  */
trait VetoableClicksUAP extends Clickable {
  def maxAllowed: Int = 1
  private var count = 0

  abstract override def click(): Unit = {
    if (count < maxAllowed) {
      count += 1
      super.click()
    }
  }
}

class ClickCountObserver extends Observer[Clickable] {
  var count = 0

  def receiveUpdate(state: Clickable): Unit = count += 1
}

object VetoableClicksUAP extends App {
  val button = new Button("Click Me!") with ObservableClicks
  with VetoableClicksUAP {
    override val maxAllowed = 2
  }
  val bco1 = new ClickCountObserver
  val bco2 = new ClickCountObserver
  button addObserver bco1
  button addObserver bco2
  (1 to 5) foreach (_ => button.click())
  assert(bco1.count == 2, s"bco1.count ${bco1.count} != 2")
  assert(bco2.count == 2, s"bco2.count ${bco2.count} != 2")
  println("Success!")

}

package com.github.zjjfly.ps.chapter9
package ui2

import com.github.zjjfly.ps.chapter9.ui.Widget
import com.github.zjjfly.ps.chapter9.ui1.Observer.{Observer, Subject, button}

/**
  * Created by zjjfly on 2017/4/16.
  */
trait Clickable {
  def click(): Unit = updateUI()

  protected def updateUI(): Unit
}

class Button(val label: String) extends Widget with Clickable {
  override protected def updateUI(): Unit = {}
}

trait ObservableClicks extends Clickable with Subject[Clickable] {
  abstract override def click(): Unit = {
    super.click()
    notifyObservers(this)
  }
}

class ClickCountObserver extends Observer[Clickable] {
  var count = 0

  def receiveUpdate(state: Clickable): Unit = count += 1
}

object Click extends App {
  val button = new Button("Click Me!") with ObservableClicks

  val bco1 = new ClickCountObserver
  val bco2 = new ClickCountObserver
  button.addObserver(bco1)
  button.addObserver(bco2)
  (1 to 5) foreach (_ => button.click())
  assert(bco1.count == 5)
  assert(bco2.count == 5)

}

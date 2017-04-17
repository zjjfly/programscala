package chapter9
package ui

/**
  * Created by zjjfly on 2017/4/15.
  */

class ButtonWihCallbacks(val label: String, val callbacks: List[() => Unit]) extends Widget {
  def click(): Unit = {
    updateUI()
    callbacks.foreach(f => f());
  }

  protected def updateUI(): Unit = {
    /* logic to change GUI appearance */
  }
}

object ButtonWihCallbacks {
  def apply(label: String, callback: () => Unit): ButtonWihCallbacks = new ButtonWihCallbacks(label, List(callback))

  def apply(label: String): ButtonWihCallbacks = new ButtonWihCallbacks(label, Nil)
}


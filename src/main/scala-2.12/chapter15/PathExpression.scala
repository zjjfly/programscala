package chapter15

import chapter15.P1.O1

/**
  * Created by zjjfly on 2017/5/26.
  */
object PathExpression extends App {
  private val t = new T1 {}
  assert(!t.c1.isInstanceOf[t.c2.type])
}

class C1 {
  var x = "1"

  def setX1(x: String): Unit = this.x = x

  def setX2(x: String): Unit = C1.this.x = x
}

trait T1 {

  class C

  val c1: C = new C
  val c2: C = new this.C //this指向的是T1这个trait
}

trait X {
  def setXX(x: String): Unit = {}
}

class C2 extends C1

class C3 extends C2 with X {
  def setX3(x: String): Unit = super.setX1(x)
  def setX4(x: String): Unit = C3.super.setX1(x)
  def setX5(x: String): Unit = C3.super[C2].setX1(x)
  def setX6(x: String): Unit = C3.super[X].setXX(x)
  // def setX7(x:String): Unit = C3.super[C1].setX1(x)    // ERROR 不能指向祖父类
  // def setX8(x:String): Unit = C3.super.super.setX1(x)  // ERROR super不能串联
}
package P1 {
  object O1 {
    object O2 {
      val name = "name"
    }
    class C1 {
      val name = "name"
    }
  }
}

class C7 {
  val name1 = P1.O1.O2.name
  type C1 = P1.O1.C1
  val c1 = new O1.C1
//  val name2=P1.O1.C1.name //ERROR P1.O1.C1不稳定
}

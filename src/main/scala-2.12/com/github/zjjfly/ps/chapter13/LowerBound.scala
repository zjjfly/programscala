package com.github.zjjfly.ps.chapter13

/**
  * Created by zjjfly on 2017/5/17.
  */
object LowerBound extends App {

  case class Parent(val value: Int) {
    override def toString = s"${this.getClass.getName}($value)"
  }

  class Child(override val value: Int) extends Parent(value)

  object Child {
    def apply(value: Int): Child = new Child(value)
  }

  val op1: Option[Parent] = Option(Child(1))
  val p1: Parent = op1.getOrElse(Parent(10))
  assert(p1 == Child(1))
  val op2: Option[Parent] = Option[Parent](null)
  val p2a = op2.getOrElse(Parent(10))
  assert(p2a == Parent(10))
  val p2b = op2.getOrElse(Child(100))
  val op3: Option[Parent] = Option[Child](null)
  val p3a: Parent = op1.getOrElse(Parent(20))
  //  val p3b:Child = op2.getOrElse(Child(200)) 不能使用Child作为返回值类型，这是因为下界类型限定，必须是Parent的超类或Parent本身
  val p3b: Parent = op2.getOrElse(Child(200))

  //无法通过编译，因为getOrElse让A处在了逆变点
  //  case class Opt[+A](value:A=null){
  //    def getOrElse(default: A) = if (value != null) value else default
  //  }
  case class Opt[A](value: A = null) {
    def getOrElse(default: A): A = if (value != null) value else default
  }

  //下面的代码无法编译，因为Opt[Child]调用getOrElse返回的只能是Child类型
  //  val p4: Parent = Opt(Child(1)).getOrElse(Parent(10))
  //  val p5:Parent=Opt[Child](null).getOrElse(Parent(10))

  val list = List(1, 2)
  val l1 = 3.0 +: list //这行代码编译的时候会警告，因为推断的类型是一个比原来更宽的类型
  val l2: List[AnyVal] = 3.0 +: list //显式的加上类型就不会有警告

  class Upper

  class Middle1 extends Upper

  class Middle2 extends Middle1

  class Lower extends Middle2

  case class C[A >: Lower <: Upper](a: A)

  class Y {
    type Z = Int
  }
}

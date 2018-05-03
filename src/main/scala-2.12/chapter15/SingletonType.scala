package chapter15

/**
  * Created by zjjfly on 2017/5/26.
  */
object SingletonType extends App {
  val s11 = new Service
  val s12 = new Service
  val l1: Service#Logger = s11.logger
  val l2: Service#Logger = s12.logger
  val l11: s11.Logger = s11.logger
  //val l11: s12.Logger = s11.logger

  def printFoo(foo: Foo.type) = println(foo)
  printFoo(Foo)
}

case object Foo {
  override def toString: String = "Foo says Hello!"
}

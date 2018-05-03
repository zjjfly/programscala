package chapter11

/**
  * Created by zjjfly on 2017/4/25.
  */
object ClassField extends App {
  class C1 {
    val name = "C1"
    var count = 0
  }
  class ClassWithC1 extends C1 {
    override val name: String = "ClassWithC1"
    count = 1
  }
  val c1 = new ClassWithC1
  println(c1.name)
  println(c1.count)
}

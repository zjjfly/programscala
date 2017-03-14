package chapter5

/**
  * Created by zjjfly on 2017/3/14.
  */
object ImplicitConversionResolution {

  case class Foo(s: String)

  object Foo {
    implicit def fromString(s: String): Foo = Foo(s)
  }

//  如果没有下面这行，使用Foo的伴生对象中的转换方法，有这一行，就是用下面这个方法
//  implicit def overrideConversion(s:String)=Foo(s)

  class O {
    def m1(foo: Foo) = println(foo)

    def m(s: String) = m1(s)
  }

  def main(args: Array[String]): Unit = {
    val o = new O()
    o.m("Hi")
  }
}

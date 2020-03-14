package com.github.zjjfly.ps.chapter13

/**
  * 使用上下文绑定代替视图绑定
  * Created by zjjfly on 2017/5/21.
  */
object View2ContextBound extends App {

  object Serialization {

    case class Rem[A](value: A) {
      def serialized: String = s"-- $value --"
    }

    type Writable[A] = A => Rem[A]

    implicit def fromInt: Writable[Int] = (i: Int) => Rem(i)

    implicit def fromFloat: Writable[Float] = (f: Float) => Rem(f)

    implicit def fromString: Writable[String] = (s: String) => Rem(s)
  }

  object RemoteConnection {

    import Serialization._

    def write[T: Writable](t: T): Unit = println(t.serialized)
  }

  RemoteConnection.write(100)
  RemoteConnection.write(3.14f)
  RemoteConnection.write("hello!")
}

package com.github.zjjfly.ps.chapter13

import com.github.zjjfly.ps.chapter13.Serialization.Writable

/**
  * Created by zjjfly on 2017/5/18.
  */
object ViewBound extends App {
  //和上下文绑定类似,视图绑定表明存在一个隐式函数可以把A转换到B
  RemoteConnection.write(1)
  RemoteConnection.write(0.1F)
  RemoteConnection.write("ss")
}

object RemoteConnection {
  //视图绑定在最新版本的Scala中已经过期，使用隐式参数代替
  def write[T <% Writable](t: T): Unit = println(t.serialized)
}

object Serialization {

  case class Writable(value: Any) {
    def serialized: String = s"-- $value --"
  }

  implicit def fromInt(i: Int): Writable = Writable(i)

  implicit def fromFloat(f: Float): Writable = Writable(f)

  implicit def fromString(s: String): Writable = Writable(s)
}

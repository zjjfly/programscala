package com.github.zjjfly.ps.chapter15

/**
  * Created by zjjfly on 2017/5/25.
  */
object PathDependentType extends App {
  val s1 = new Service
  //下面的代码会报错，因为不同Service对象的logger成员的类型是不同的
  //val s2 = new Service {
  //  override val logger: s1.Logger = s1.logger
  //}
}

class Service {

  class Logger {
    def log(message: String): Unit = {
      println(s"log: $message")
    }
  }

  val logger: Logger = new Logger
}

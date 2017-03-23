package chapter5

import java.lang.Iterable
import java.util
import java.util.concurrent.{Executor, Executors}

import play.api.libs.json._

/**
  * 自定义字符串插补器
  * Created by zjjfly on 2017/3/14.
  */

/**
  * 实现参考了[[http://www.scala-lang.org/api/current/scala/StringContext.html]]的例子
  *
  */
object Interpolators {

  implicit class jsonForStringContext(val sc: StringContext) {
    def json(values: Any*): JsValue = {
      val keyRE ="""^[\s{,]*(\S+):\s*""".r
      val keys = sc.parts map {
        case keyRE(key) => key
        case str => str
      }
      Json.toJson(keys.zip(values.map(_.toString)).toMap[String, String])
    }
  }

}

object InterpolatorTest extends App {

  import Interpolators._

  val name = "Dean Wampler"
  val book = "Programming Scala,2nd Edition"
  val json = json"{name:${name},book:$book"
  println(json)
}

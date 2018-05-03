package chapter13

import java.util.Comparator

/**
  * Created by zjjfly on 2017/5/18.
  */
object Implicitly extends App {
  //implicity的三种用法：
  //1.找到隐式转换后的类型
  "abc".capitalize
  val f = implicitly[String => { def capitalize: String }]
  assert(
    f("aa").getClass().getName == "scala.collection.immutable.WrappedString") //

  //2.和上下文绑定一起使用
  def max[T: Comparator](a: T, b: T) = {
    if (implicitly[Comparator[T]].compare(a, b) > 0) a else b
  }

  //3.显式的传递隐式参数
  trait Show[T] { def show(t: T): String }
  object Show {
    implicit def IntShow: Show[Int] = new Show[Int] {
      def show(i: Int) = i.toString
    }
    implicit def StringShow: Show[String] = new Show[String] {
      def show(s: String) = s
    }

    def ShoutyStringShow: Show[String] = new Show[String] {
      def show(s: String) = s.toUpperCase
    }
  }

  case class Person(name: String, age: Int)
  object Person {
    implicit def PersonShow(implicit si: Show[Int],
                            ss: Show[String]): Show[Person] = new Show[Person] {
      def show(p: Person) =
        "Person(name=" + ss.show(p.name) + ", age=" + si.show(p.age) + ")"
    }
  }

  val p = Person("bob", 25)
  println(implicitly[Show[Person]].show(p))
  println(
    Person.PersonShow(si = implicitly, ss = Show.ShoutyStringShow).show(p))
}

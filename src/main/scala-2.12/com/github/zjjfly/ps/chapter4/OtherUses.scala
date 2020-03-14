package com.github.zjjfly.ps.chapter4

/**
  * Created by zjjfly on 2016/11/29.
  */
object OtherUses {
  def main(args: Array[String]): Unit = {
    //模式匹配的其他用法
    val Person(name, age, Address(_, state, _)) =
      Person("Dean", 29, Address("1 Scala Way", "CA", "USA"))
    println(name + "," + age + "," + state)
    val head +: tail = List(2, 3)
    println(head)
    println(tail)
    val head1 +: head2 +: tails = Vector(1, 2, 3)
    println(head1 + "," + head2 + "," + tail)
    val Seq(a, b, c) = List(1, 2, 3)
    val p = Person("Dean", 29, Address("1 Scala Way", "CA", "USA"))
    val re =
      if (p == Person("Dean", 29, Address("1 Scala Way", "CA", "USA"))) "yes"
      else "no"
    println(re)
    //下面这种语法常用
    val (sum, count) = sum_count(List(1, 2, 3, 4, 5))
    println(sum + "," + count)

    val as = Seq(Address("1 Scala Lane", "Anytown", "USA"),
                 Address("2 Clojure Lane", "Othertown", "USA"))
    val ps = Seq(Person("Buck Trends", 29), Person("Clo Jure", 28))
    val pas = ps zip as
    pas map {
      case (Person(name, age, address), Address(street, city, country)) =>
        s"$name (age:$age) lives at $street,$city,$country"
    } foreach println
  }

  def sum_count(ints: Seq[Int]) = (ints.sum, ints.size)
}

case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int, address: Address = null)

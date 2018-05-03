package chapter10

/**
  * Created by zjjfly on 2017/4/20.
  */
object Equality extends App {

  case class Person(firstName: String, lastName: String, age: Int)

  val p1a = Person("Dean", "Wampler", 29)
  val p1b = Person("Dean", "Wampler", 29)
  val p2 = Person("Buck", "Trends", 30)

  assert(p1a equals p1a)
  assert(p1a equals p1b)
  assert(!(p1a equals p2))
  assert(!(p1a equals null))
  //  null equals p1a throws java.lang.NullPointerException
  //  null equals null throws java.lang.NullPointerException

  assert(p1a == p1a)
  assert(p1a == p1b)
  assert(p1a != p2)

  assert(p1a eq p1a)
  assert(p1a ne p1b)
  assert(p1a ne p2)
  assert(p1a ne null)
  assert(null ne p1a)
  assert(null eq null)

  assert(Array(1, 2) != Array(1, 2))
  assert(Array(1, 2) sameElements Array(1, 2))
}

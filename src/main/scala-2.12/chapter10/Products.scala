package chapter10

/**
  * Created by zjjfly on 2017/4/19.
  */
object Products extends App {

  case class Boy(name: String, age: Int)

  val b: Product = Boy("jjzi", 27)
  assert(2 == b.productArity) //实例的字段的数量
  assert("jjzi" == b.productElement(0))
  //获取字段，从0开始
  val element: Any = b.productElement(1) //这种机制有一个限制，返回值类型都是Any，而不是具体类型
  assert(27 == element)
  b.productIterator.foreach(println) //productIterator返回字段的Iterator
}

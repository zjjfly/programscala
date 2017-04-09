package chapter7

/**
  * Created by zjjfly on 2017/4/5.
  */
object Option extends App {

  val results: Seq[Option[Int]] = Vector(Some(10), None, Some(20))
  val results2 = for {Some(i) <- results
  } yield 2 * i
  assert(results2 == Vector(20, 40))

  def positive(i: Int): Option[Int] = if (i > 0) Some(i) else None

  val result = for {
    i1 <- positive(5)
    i2 <- positive(10 * i1)
    i3 <- positive(25 * i2)
    i4 <- positive(2 * i3)
  } yield i1 + i2 + i3 + i4
  assert(result.contains(3805))
}

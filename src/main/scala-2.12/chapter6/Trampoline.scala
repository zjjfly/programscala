package chapter6

/**
  * 蹦床
  * Created by zjjfly on 2017/3/22.
  */
object Trampoline extends App {

  import scala.util.control.TailCalls._

  def isEven(xs: List[Int]): TailRec[Boolean] = {
    if (xs.isEmpty) done(true)
    else tailcall(isOdd(xs.tail))
  }

  def isOdd(xs: List[Int]): TailRec[Boolean] = {
    if (xs.isEmpty) done(false)
    else tailcall(isEven(xs.tail))
  }

  for (elem <- 1 to 5) {
    val even = isEven(1 to elem toList).result
    assert(even == (elem % 2 == 0))
  }

}

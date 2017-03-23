package chapter5

/**
  * 蹦床
  * Created by zjjfly on 2017/3/22.
  */
object Trampoline {

  import scala.util.control.TailCalls._

  def isEven(xs: List[Int]): TailRec[Boolean] = {
    if (xs.isEmpty) done(true)
    else tailcall(isOdd(xs.tail))
  }

  def isOdd(xs: List[Int]): TailRec[Boolean] = {
    if (xs.isEmpty) done(false)
    else tailcall(isEven(xs.tail))
  }

  def main(args: Array[String]): Unit = {
    for (elem <- 1 to 5) {
      val even=isEven(1 to elem toList).result
      println(s"$elem is even?${even}")
    }
  }

}

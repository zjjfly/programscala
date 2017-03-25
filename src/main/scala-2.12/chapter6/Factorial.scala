package chapter6

import scala.annotation.tailrec

/**
  * Created by zjjfly on 2017/3/22.
  */
object Factorial extends App {
  @tailrec
  def fact(i: BigInt, accumulator: BigInt): BigInt = {
    if (i == 1) accumulator
    else fact(i - 1, i * accumulator)
  }

  for (i <- 1 to 10) {
    println(s"$i:\t${fact(i, 1)}")
  }

}

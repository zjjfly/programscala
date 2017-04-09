package chapter7

import scalaz._,std.AllInstances._

/**
  * Created by zjjfly on 2017/4/8.
  */
object ScalazValidation extends App {
  def positive(i: Int): Validation[List[String], Int] = {
    if (i > 0) Success(i) else Failure(List(s"Nonpositive integer $i"))
  }

  import scalaz.Validation.FlatMap._

  val result1: Validation[List[String], Int] = for {
    i1 <- positive(5)
    i2 <- positive(10 * i1)
    i3 <- positive(25 * i2)
    i4 <- positive(2 * i3)
  } yield i1 + i2 + i3 + i4
  assert(result1 == Success(3805))
  val result2: Validation[List[String], Int] = for {
    i1 <- positive(5)
    i2 <- positive(-1 * i1)
    i3 <- positive(25 * i2)
    i4 <- positive(-2 * i3)
  } yield i1 + i2 + i3 + i4
  assert(result2 == Failure(List("Nonpositive integer -5")))
  //Validation可以累加
  val result3 = positive(5) +++ positive(10) +++ positive(25)
  assert(result3 == Success(40))
  val result4 = positive(-1) +++ positive(10) +++ positive(-5)
  assert(result4 == Failure(List("Nonpositive integer -1","Nonpositive integer -5")))

}

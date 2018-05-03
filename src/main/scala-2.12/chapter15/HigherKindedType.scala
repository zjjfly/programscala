package chapter15

/**
  * Created by zjjfly on 2017/5/28.
  */
import Reduce._
object HigherKindedType extends App {
  def sum[T: Add, M[T]](m: M[T])(implicit reduce: Reduce[T, M]): T = {
    reduce.reduce(m)(implicitly[Add[T]].add)
  }
  import Add._
  assert(sum(Vector(1 -> 10, 2 -> 20, 3 -> 30)) == 6 -> 60)
  assert(sum(1 to 10) == 55)
  assert(sum(Option(2)) == 2)
//sum[Int,Option](None) //ERROR

  //使用简化版的Reduce
  def sum1[T: Add, M[_]: Reduce1](m: M[T]): T = {
    implicitly[Reduce1[M]].reduce(m)(implicitly[Add[T]].add)
  }
  assert(sum1(Vector(1 -> 10, 2 -> 20, 3 -> 30)) == 6 -> 60)
  assert(sum1(1 to 10) == 55)
  assert(sum1(Option(2)) == 2)
}

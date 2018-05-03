package chapter15

/**
  * Created by zjjfly on 2017/5/28.
  */
trait Add[T] {
  def add(t1: T, T2: T): T
}
object Add {
  implicit val addInt = new Add[Int] {
    override def add(t1: Int, t2: Int): Int = t1 + t2
  }
  implicit val addIntPair = new Add[(Int, Int)] {
    override def add(p1: (Int, Int), p2: (Int, Int)): (Int, Int) =
      (p1._1 + p2._1, p1._2 + p2._2)
  }
}
object Main extends App {
  def sumSeq[T: Add](seq: Seq[T]) = {
    seq reduce (implicitly[Add[T]].add)
  }

  println(sumSeq(Vector(1, 2, 3)))
  println(sumSeq(1 to 100))
//  println(sumSeq(List("a","b","c")))//ERROR
}

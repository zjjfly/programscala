package com.github.zjjfly.ps.chapter6

/**
  * Created by zjjfly on 2017/3/29.
  */
object Fold extends App {
  val list = List(1, 2, 3, 4, 5, 6)
  assert(list.reduce(_ + _) == 21)
  assert(list.fold(10)(_ * _) == 7200)
  assert((list fold 10)(_ * _) == 7200)
  assert(List.empty.fold(0)(_ + _) == 0)
  try {
    List.empty[Int].reduce(_ + _) //空集合不能调用reduce，否则会报错
  } catch {
    case e: UnsupportedOperationException => println(e.getMessage)
  }
  assert(List.empty[Int].reduceOption(_ + _).isEmpty)
  assert(List(1, 2, 3, 4, 5).reduceOption(_ + _).contains(15))
  val list1 = (List(1, 2, 3, 4, 5, 6) foldRight List.empty[String]) {
    (i, list) =>
      "[" + i + "]" :: list
  }
  assert(list1 == List("[1]", "[2]", "[3]", "[4]", "[5]", "[6]"))
  assert(
    List(List(1, 3, 4), List(2, 4, 5)).aggregate(0)(
      (b, list) => b + list.sum,
      _ + _) == 19) //aggregate用的不多，因为它的参数多而且不容易理解
  assert(List(1, 2, 3, 4).scan(0)(_ + _) == List(0, 1, 3, 6, 10))

  assert(list.reduceLeft(_ + _) == list.reduceRight(_ + _))
  assert(list.reduceLeft(_ - _) != list.reduceRight(_ - _))
  val list2 = list map (_.toString)
  assert(list2.reduceLeft((x, y) => s"($x)-($y)") != list2.reduceRight((x, y) =>
    s"($x)-($y)"))

  //reduceRight其实也可以使用尾递归实现，但是效率可能并不高，因为:+的效率不高
  def reduceRight[A, B](s: Seq[A])(f: A => B): Seq[B] = {
    @annotation.tailrec
    def rr(accum: Seq[B], s2: Seq[A]): Seq[B] = s2 match {
      case head :+ last => rr(f(last) +: accum, head)
      case _            => accum
    }

    rr(Seq.empty[B], s)
  }

  /**
    * ScalaDoc中的例子[[http://www.scala-lang.org/api/current/scala/collection/immutable/Stream.html]]
    */
  val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs
    .zip(fibs.tail)
    .map(n => {
      println("Adding %d and %d".format(n._1, n._2))
      n._1 + n._2
    })
  fibs.take(10).foreach(_ => ())
  private val s: String = ""
}

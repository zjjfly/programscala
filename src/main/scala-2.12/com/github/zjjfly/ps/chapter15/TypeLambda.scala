package com.github.zjjfly.ps.chapter15

/**
  * Created by zjjfly on 2017/5/30.
  */
object TypeLambda extends App {

  trait Functor[A, +M[_]] {
    def map2[B](f: A => B): M[B]
  }

  object Functor {

    implicit class SeqFunctor[A](seq: Seq[A]) extends Functor[A, Seq] {
      def map2[B](f: A => B): Seq[B] = seq map f
    }

    implicit class OptionFunctor[A](opt: Option[A]) extends Functor[A, Option] {
      def map2[B](f: A => B): Option[B] = opt map f
    }

    implicit class MapFunctor[K, V1](mapKV1: Map[K, V1])
        extends Functor[V1, ({ type λ[α] = Map[K, α] })#λ] {
      override def map2[B](f: (V1) => B): Map[K, B] = mapKV1 map {
        case (k, v) => (k, f(v))
      }
    }

  }

  import Functor._

  println((List(1, 3, 4) map2 (_ * 2))) //List(2,6,8)
  println(Option(2) map2 (_ * 2)) //Some(4)
  val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
  println(m map2 (_ * 2)) // Map(one -> 2, two -> 4, three -> 6)

}

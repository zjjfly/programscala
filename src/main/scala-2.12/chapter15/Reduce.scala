package chapter15

/**
  * Created by zjjfly on 2017/5/28.
  */
trait Reduce[T, -M[T]] {
  def reduce(m: M[T])(f: (T, T) => T): T
}
object Reduce {
  implicit def seqReduce[T] = new Reduce[T, Seq] {
    override def reduce(m: Seq[T])(f: (T, T) => T): T = m reduce f
  }
  implicit def optionReduce[T] = new Reduce[T, Option] {
    override def reduce(m: Option[T])(f: (T, T) => T): T = m reduce f
  }
}

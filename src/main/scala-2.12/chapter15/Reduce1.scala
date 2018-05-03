package chapter15

/**
  * 简化版的Reduce
  * Created by zjjfly on 2017/5/30.
  */
trait Reduce1[-M[_]] {
  def reduce[T](m: M[T])(f: (T, T) => T): T
}

object Reduce1 {
  implicit val seqReduce = new Reduce1[Seq] {
    override def reduce[T](m: Seq[T])(f: (T, T) => T): T = m reduce f
  }

  implicit val optionReduce = new Reduce1[Option] {
    override def reduce[T](m: Option[T])(f: (T, T) => T): T = m reduce f
  }
}

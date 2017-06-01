package chapter15

/**
  * Created by zjjfly on 2017/5/31.
  */
object SelfRecursiveType extends App{
}
trait Doubler[T <: Doubler[T]] {self:T=>
  def double: T
}

case class Square(base: Double) extends Doubler[Square] {
  override def double: Square = Square(base * 2)
}

case class Apple(kind: String) extends Doubler[Apple] {
  override def double: Apple = Apple(kind)
}
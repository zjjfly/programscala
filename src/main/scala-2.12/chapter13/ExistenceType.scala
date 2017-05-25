package chapter13

/**
  * Created by zjjfly on 2017/5/25.
  */
object ExistenceType extends App{
  object Doubler{
    def double(seq: Seq[_]):Seq[Int]=seq match {
      case Nil=>Nil
      case head +: tail=>toInt(head) +: double(tail)
    }
    def  toInt(x:Any):Int=x match {
      case i:Int=>i
      case s:String=>s.toInt
      case x=>throw new RuntimeException(s"Unexpected list element $x")
    }
  }
}

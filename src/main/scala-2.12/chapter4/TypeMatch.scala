package chapter4

/**
  * Created by zjjfly on 2016/11/29.
  */
object TypeMatch {
  def main(args: Array[String]): Unit = {
//    val tuples1: Seq[(String, List[Any])] = for {
//      x <- Seq(List(5.5, 5.6, 5.7), List("a", "b"))} yield x match {
//      case seqd: Seq[Double] => ("seq double", seqd)
//      case seqs: Seq[String] => ("seq string", seqs)
//      case _ => ("unknown!", x)
//    }
//    println(tuples1)

    //上面的代码会在编译的时候有警告,而且结果是两个都被认为是seq double
    //原始是jvm的类型擦除机制,使得第二个case成了unreachable
    //解决办法是先匹配Seq,然后使用一个嵌套的match匹配类型
    val tuples2: Seq[(String, List[Any])]=for {
      x <- Seq(List(5.5, 5.6, 5.7), List("a", "b"))} yield x match {
      case seq: Seq[_] => (s"seq ${doSeqMatch(seq)}", seq)
      case _ => ("unknown!", x)
    }
    println(tuples2)
  }

  def doSeqMatch[T](seq: Seq[T]): String = seq match {
    case Nil => "Nothing"
    case head +: _ => head match {
      case _: Double => "Double"
      case _: String => "String"
      case _ => "Unmatched seq element"
    }
  }
}

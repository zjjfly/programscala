package chapter4

/**
  * Created by zjjfly on 2016/11/28.
  */
object SeqMatch {
  def main(args: Array[String]): Unit = {
    //现在使用Seq更方便,它实际返回的是List
    val nonEmptySeq = Seq(1, 2, 3, 4, 5)
    val emptySeq = Seq.empty[Int]
    val nonEmptyList = List(1, 2, 3, 4, 5)
    val emptyList = Nil
    val nonEmptyVector = Vector(1, 2, 3, 4, 5)
    val emptyVector = Vector.empty[Int]
    val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val emptyMap = Map.empty[String, Int]
    for (seq <- Seq(nonEmptySeq,
                    nonEmptyList,
                    nonEmptyVector,
                    nonEmptyMap.toSeq)) {
      println(seqToString(seq))
    }
    println()
    for (seq <- Seq(nonEmptySeq,
                    nonEmptyList,
                    nonEmptyVector,
                    nonEmptyMap.toSeq)) {
      println(reverseSeqToString(seq))
    }
    println()
    for (seq <- Seq(nonEmptySeq,
                    nonEmptyList,
                    nonEmptyVector,
                    nonEmptyMap.toSeq)) {
      println(windows(seq))
    }
    //windows函数的功能如此常用,所以scala提供了sliding方法
    println(nonEmptyList.sliding(2).toList)
    println(nonEmptyList.sliding(3, 2).toList)

    //匹配可变参数
    val whereIn = WhereIn("stat", "IL", "CA", "VA")
    whereIn match {
      //这个@实际作用是把_*绑定到vals变量
      case WhereIn(col, val1, vals @ _*) =>
        println(s"Where $col in (${(val1 +: vals) mkString ","})")
      case _ => println(s"ERROR: Unknown expression: $whereIn")
    }
  }

  def seqToString[T](seq: Seq[T]): String = seq match {
    case head +: tail => s"$head +: " + seqToString(tail)
    case Nil          => "Nil"
  }

  def reverseSeqToString[T](seq: Seq[T]): String = seq match {
    case init :+ last => reverseSeqToString(init) + s" :+ $last"
    case Nil          => "Nil"
  }

  def windows[T](seq: Seq[T]): String = seq match {
    case Seq(head1, head2, _*) => //
      s"($head1, $head2), " + windows(seq.tail)
    case Seq(head, _*) =>
      s"($head, _), " + windows(seq.tail)
    case Nil => "Nil"
  }

}

case class WhereIn[T](columnName: String, val1: T, vals: T*)

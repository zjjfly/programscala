package chapter6

/**
  * Created by zjjfly on 2017/3/25.
  */
object Sequence extends App {

  //大多数集合都有empty方法用于生成空集合
  val seq1 = "Programming" +: "Scala" +: Seq.empty
  //+:是Seq的cons方法，类似List的::
  val seq2 = "People" +: "should" +: "read" +: Seq.empty

  val list1 = List(1, 2)
  val list2 = 3 +: list1//在头部加一个元素
  assert(List(3, 1, 2) == list2)
  val list3 = list1 :+ 3//在尾部加一个元素
  assert(List(1, 2, 3) == list3)
}

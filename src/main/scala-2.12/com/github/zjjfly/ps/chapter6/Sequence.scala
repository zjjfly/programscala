package com.github.zjjfly.ps.chapter6

/**
  * Created by zjjfly on 2017/3/25.
  */
object Sequence extends App {

  //大多数集合都有empty方法用于生成空集合
  val seq1 = "Programming" +: "Scala" +: Seq.empty
  //+:是Seq的cons方法，类似List的::
  val seq2 = Seq.empty :+ "People" :+ "should" :+ "read"

  val seq3 = seq1 ++ seq2

  //使用Vector的好处是，它的操作的时间复杂度都是O(1)
  val vec1 = "Programming" +: "Scala" +: Vector.empty

  val vec2 = Vector.empty :+ "People" :+ "should" :+ "read"

  val vec3 = vec1 ++ vec2

  val list1 = List(1, 2)
  val list2 = 3 +: list1 //在头部加一个元素
  assert(List(3, 1, 2) == list2)
  val list3 = list1 :+ 3 //在尾部加一个元素
  assert(List(1, 2, 3) == list3)
}

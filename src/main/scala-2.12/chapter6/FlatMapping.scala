package chapter6

/**
  * Created by zjjfly on 2017/3/28.
  */
object FlatMapping extends App {

  val list1 = List("now", "is", "", "the", "time")
  val list2 = list1.flatMap(s => s.toList)
  assert(
    list2 == List('n', 'o', 'w', 'i', 's', 't', 'h', 'e', 't', 'i', 'm', 'e'))
  val list3 = list1.map(_.toList).flatten
  assert(list2 == list3)
}

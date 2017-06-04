package chapter12

import scala.collection.immutable.TreeSet

/**
  * Created by zjjfly on 2017/5/5.
  */
object CanBuildFrom extends App {
  val set = collection.BitSet(1, 2, 3, 4, 5)
  val treeSet = set map (_.toString)
  assert(treeSet.isInstanceOf[TreeSet[Int]])
  val indexSeq = "xyz" map (_.toInt)
  assert(indexSeq.isInstanceOf[Vector[_]])
}

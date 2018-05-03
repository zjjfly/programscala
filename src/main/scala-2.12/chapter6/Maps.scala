package chapter6

/**
  * Created by zjjfly on 2017/3/26.
  */
object Maps extends App {
  val nums1 = Map("1" -> 1, "2" -> 2)
  val nums2 = nums1.map(kv => kv._1 + kv._2)
  val nums3 = nums1.map {
    case (k, v) => k + v //使用partial function的好处是可以使用模式匹配
  }
  assert(nums2 == nums3)
  val nums4 = nums1 + ("3" -> 3)
  assert(nums4 == Map("1" -> 1, "2" -> 2, "3" -> 3))
  val nums5 = nums1 - ("1")
  assert(nums5 == Map("2" -> 2))
}

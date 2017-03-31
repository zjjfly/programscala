package chapter6

/**
  * Created by zjjfly on 2017/3/29.
  */
object Filter extends App {
  val stateCapitals = Map("Alabama" -> "Montgomery", "Alaska" -> "Juneau", "Wyoming" -> "Cheyenne")
  val map2 = stateCapitals filter { case (k, _) => k.startsWith("A") }
  assert(map2 == Map("Alabama" -> "Montgomery", "Alaska" -> "Juneau"))
  val map3 = stateCapitals drop 1
  assert(map3 == Map("Alaska" -> "Juneau", "Wyoming" -> "Cheyenne"))
  val map4 = stateCapitals.dropWhile { case (k, _) => k.startsWith("A") }
  assert(map4 == Map("Wyoming" -> "Cheyenne"))
  assert(stateCapitals.exists { case (k, _) => k == "Alabama" })
  assert(stateCapitals.filterNot { case (k, _) => k.startsWith("A") } == Map("Wyoming" -> "Cheyenne"))
  assert(stateCapitals.find(kv => kv._1.equals("Alabama")).contains("Alabama" -> "Montgomery"))
  assert(stateCapitals.forall(kv => kv._1.length > 3))
  assert(stateCapitals.partition(kv => kv._1.startsWith("A")) == (Map("Alabama" -> "Montgomery", "Alaska" -> "Juneau"), Map("Wyoming" -> "Cheyenne")))
  assert(stateCapitals.take(1)==Map("Alabama" -> "Montgomery"))
  assert(stateCapitals.takeWhile(kv=>kv._1.startsWith("A"))==Map("Alabama" -> "Montgomery", "Alaska" -> "Juneau"))
}

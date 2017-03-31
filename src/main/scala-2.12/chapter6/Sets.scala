package chapter6

/**
  * Created by zjjfly on 2017/3/27.
  */
object Sets extends App {
  val states = Set("Alabama", "Alaska", "Wyoming")
  val lengths = states map (_.length)
  val states2 = states + "Virginia"
  assert(Set("Alabama", "Alaska", "Wyoming", "Virginia") == states2)
  val states3 = states2 + ("New York", "Illinois")
  assert(Set("Alabama", "Alaska", "Wyoming", "Virginia", "New York", "Illinois") == states3)
  assert(states("Alabama"))
}

package com.github.zjjfly.ps.chapter6

/**
  * Created by zjjfly on 2017/3/25.
  */
object Partial2Optional extends App {
  val finicky: PartialFunction[String, String] = {
    case "finicky" => "FINICKY"
  }
  assert(finicky("finicky") == "FINICKY")
  assert(!finicky.isDefinedAt("other"))
  val finickyOption: (String) => Option[String] = finicky.lift
  assert(finickyOption("finicky").get == "FINICKY")
  assert(finickyOption("other").isEmpty)
  val finicky2: PartialFunction[String, String] = Function.unlift(finickyOption)
  assert(finicky2("finicky") == "FINICKY")
  assert(!finicky2.isDefinedAt("other"))
}

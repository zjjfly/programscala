package com.github.zjjfly.ps.Chapter2

/**
  * Created by zjjfly on 2016/11/1.
  */
object Optionals {
  def main(args: Array[String]): Unit = {
    val stateCapitals = Map(
      "Alabama" -> "Montgomery",
      "Alaska" -> "Juneau",
      "Wyoming" -> "Cheyenne"
    )
    println("Get the capitals wrapped in Options:")
    println("Alabama: " + stateCapitals.get("Alabama"))
    println("Wyoming: " + stateCapitals.get("Wyoming"))
    println("Unkown: " + stateCapitals.get("Unkown"))

    println("Get the capitals themselves out of the Options:")
    println("Alabama: " + stateCapitals("Alabama"))
    println("Wyoming: " + stateCapitals.getOrElse("Wyoming", "Oops!"))
    println("Unknown: " + stateCapitals.getOrElse("Unknown", "Oops2!"))
  }
}

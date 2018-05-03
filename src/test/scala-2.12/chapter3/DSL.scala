package chapter3

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by zjjfly on 2016/11/4.
  */
class DSL extends FunSpec with Matchers {
  describe("nerd finder") {
    it("identify nerds from a List") {
      val actors = List("Rick Moranis", "James Dean", "Woody Allen")
      actors shouldEqual List("Rick Moranis", "James Dean", "Woody Allen")
    }
  }
}

package chapter3

import org.scalatest.{FunSpec,MustMatchers}

/**
  * Created by zjjfly on 2016/11/4.
  */
class DSL extends FunSpec with MustMatchers{
  describe("nerd finder"){
    it ("identify nerds from a List") {
      val actors=List("Rick Moranis","James Dean","Woody Allen")
      actors mustEqual  List("Rick Moranis","James Dean","Woody Allen")
    }
  }
}
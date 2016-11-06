package Chapter3

import org.scalatest.{FunSpec, ShouldMatchers}

/**
  * Created by zjjfly on 2016/11/4.
  */
class DSL extends FunSpec with ShouldMatchers{
  describe("nerd finder"){
    it ("identify nerds from a List") {
      val actors=List("Rick Moranis","James Dean","Woody Allen")
      actors shouldEqual List("Rick Moranis","James Dean","Woody Allen")
    }
  }
}
package chapter4

/**
  * Created by zjjfly on 2016/11/25.
  */
object ValueMatch {
  def main(args: Array[String]): Unit = {
    checkY(100)
    for {
      x <- Seq(1, 2, 2.7, "one", "two", 'four)
    } {
      val str = x match {
          //同时匹配多个条件
        case _: Int | _: Double => "a number: " + x
        case "one" => "string one"
        case _: String => "other string: " + x
        case _ => "unexpected value: " + x
      }
      println(str)
    }
  }

  def checkY(y: Int) = {
    for {
      x <- Seq(99, 100, 101)
    } {
      val str = x match {
        //模式匹配中要引用之前的变量,需要把变量名用反引号包起来
        case `y` => "found y!"
        case i: Int => "int:" + i
      }
      println(str)
    }
  }
}

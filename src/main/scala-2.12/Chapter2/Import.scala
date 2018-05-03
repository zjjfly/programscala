package Chapter2

import java.io.File._ //导入File类中的所以静态成员
import scala.collection.mutable._
import collection.immutable._ //scala的导入时相对的.scala包已经导入了,所以可以不用写
import _root_.scala.collection.parallel._ //全路径,从真正的root开始写
/**
  * Created by zjjfly on 2016/11/2.
  */
object Import {
  def main(args: Array[String]): Unit = {
    println(separatorChar)
    stuffWithBigInteger()
    val fs = new Array[() => Int](4)
  }

  def stuffWithBigInteger() = {
    import java.math.BigInteger.{ONE => _, TEN, ZERO => JAVAZERO}
    println("TEN:" + TEN)
    println("ZERO:" + JAVAZERO)
  }
}

package chapter8

/**
  * Created by zjjfly on 2017/4/10.
  */
object ValueClass extends App {

  //value class不会被放入堆内存，而是放在栈中，但对它的声明有很多限制
  class Dollar(val value: Float) extends AnyVal {
    override def toString: String = "$%.2f".format(value)
  }

  //参数不一定要是Scala的值类型，也可以是引用类型
  class USPhoneNumber(val s: String) extends AnyVal {
    override def toString = {
      val digs = digits(s)
      val areaCode = digs.substring(0, 3)
      val exchange = digs.substring(3, 6)
      val subnumber = digs.substring(6, 10)
      s"($areaCode) $exchange-$subnumber"
    }

    private def digits(str: String): String = str.replaceAll("""\D""", "")
  }

}


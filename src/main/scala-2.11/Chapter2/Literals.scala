package Chapter2

/**
  * Created by zjjfly on 16/8/5.
  */
object Literals {
  def main(args: Array[String]) {
    val c: Char = '\u0009' //这个实际在编译的时候会转成实际代表的字符,所以不建议这样写,直接写\t更好
    println(c)
    val s1 =
      """adad\ta
        *adda
        *add
      """.stripMargin('*')
    //转义符失效了
    //stripMargin方法会消除leading char前的空白,默认的leading char是|,也可以通过给方法传入一个Char自定义
    println(s1)
    //如果想要去掉前缀和后缀,可以使用下面这种方法,但只去掉整个字符串的首尾,不是单独每一行的
    val s2 =
      """xxxGoodbye, Scalayyy
        xxxCome again!yyy""".stripPrefix("xxx").stripSuffix("yyy")
    println(s2)
    val i='id
    println(i)
    //这种方法生成Symbol只能包含数字,字母或下划线,不能有空格,且第一个字符不能是数字
    //想要没有上面这些限制,使用Symbol.apply方法
    val symbol: Symbol = Symbol("1 id")
    println(symbol)

    val (t1,t2,t3)=(1,2,3)
    println( t1 + ", " + t2 + ", " + t3 )
  }
}

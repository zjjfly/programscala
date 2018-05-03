package Chapter3

/**
  * Created by zjjfly on 2016/11/2.
  */
object Postfix {
  def main(args: Array[String]): Unit = {
    //postfix在2.10开始是一个可选feature,直接使用会在编译的时候会有警告⚠️
    //去除警告的方法有两种
    //1.import
    import scala.language.postfixOps
    println(1 toString)
    println(1 toString)
    //2.在编译的时候加上-language:postfixOps
    //第一种更好,可以让读者知道我使用了这个特性

    //_告诉编译器到下一个空位为止的所以字符都是标识符的一部分
    val xyz_++= = 1 //可以通过编译
    //    val xyz++= =1//通不过编译
    //如果标识符的开头是一个操作符,那么之后的字符只能是操作符
    val --+- = 1

    //可以使用`xxx`给断言命名,一般用来给测试代码命名
    def `test that addition works`() = assert(1 + 1 == 2)
    `test that addition works`()
    //模式匹配中,小写字母开头的标识符解析为变量,大写字母开头的标识符解析为常量(如类名)
    val x = Option(1)
    x match {
      case Some(i) => println("x = " + i)
      case None    => println("x is None")
    }

    //
  }
}

package Chapter3

/**
  * Created by zjjfly on 2016/11/2.
  */
object EmptyArgMethod {
  def main(args: Array[String]): Unit = {
    println(test1)
    println(test2)
    println(test2())
  }
  //如果方法没有参数,可以省略(),但调用的时候必须也省略括号
  def test1 = 1
  //如果不省略(),那么调用的时候可以省略也可以不省略
  def test2() = 2
  //一般的规则是:有副作用的方法定义的时候不省略(),没有副作用的方法省略()
  //也可以给编译器加上-Xlint参数,如果我们定义有副作用方法的时候省略了()会给警告
}

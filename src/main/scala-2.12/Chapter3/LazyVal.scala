package Chapter3

/**
  * Created by zjjfly on 2016/11/21.
  */
object LazyVal {

  def init(): Int = {
    0
  }

  def main(args: Array[String]): Unit = {
    //lazy表示这个值在真正需要它的时候才去初始化,并且只初始化一次.
    //对于可变量来说,使用lazy没多大意义,所以lazy只能修饰val值
    //只有当一个值的初始化开销很大或者使用lazy是最简单的实现按特定顺序初始化的方案的时候,才使用lazy
    lazy val resource: Int = init()
  }
}

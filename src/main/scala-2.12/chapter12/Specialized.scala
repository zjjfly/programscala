package chapter12

/**
  * Created by zjjfly on 2017/5/5.
  */
object Specialized extends App {
  //@specialized会让编译器生成这个类的针对某些类型的特化版本，由此提高性能
  class SpecialVector[@specialized(Int, Double, Boolean) T]
}

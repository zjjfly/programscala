package chapter6

/**
  * Created by zjjfly on 2017/3/23.
  */
object PartialAppliedFunction extends App{

  def cat(s1:String)(s2:String)=s1+s2

  //为一个已有的多参数列表的函数提供部分参数列表而不是全部的，会得到一个新的函数，这种函数就叫部分应用函数。
  //只有一个参数列表的函数无法产生部分应用函数
  val hello=cat("Hello,")_//为防止产生歧义，_号是必须的

  assert(cat("Hello,")("World")==hello("World"))

}

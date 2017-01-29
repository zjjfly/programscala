package chapter5

/**
  * Created by zjjfly on 2017/1/29.
  */
object M{
//  java会在编译的时候把参数化类型的类型参数
//  下面这样定义方法，会因为类型擦除，导致这两个方法的编译结果一样，编译器就会报错了
//  def m(seq: Seq[Int]):Unit= println(s"Seq[Int]:$seq")
//  def m(seq: Seq[String]):Unit= println(s"Seq[String]:$seq")

  implicit object IntMarker
  implicit object StringMarker

  //使用隐式参数，可以解决上面的编译问题
  //不使用Int，String这样的类型的隐式参数的原因是，这些类型更有可能会在多处定义器对应的隐式对象。如果这些隐式定义被导入相同的作用域，会导致程序bug或者编译错误
  def m(seq: Seq[Int])(implicit i:IntMarker.type):Unit= println(s"Seq[Int]:$seq")
  def m(seq: Seq[String])(implicit i:StringMarker.type):Unit= println(s"Seq[String]:$seq")
}
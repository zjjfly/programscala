package chapter24


/**
  * Created by zjjfly on 2017/6/5.
  */

import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._

object TypeTag4MatchType extends App {
  def toType2[T](t: T)(implicit tag: TypeTag[T]): universe.Type = tag.tpe

  def toType[T: TypeTag](t: T): universe.Type = typeOf[T] //typeOf[T]是implicitly[TypeTag[T]].tpe的缩写

  println(toType(1))
  println(toType(true))
  println(toType(Seq(1, true, 3.14)))
  println(toType((i: Int) => i.toString))

  //比较类型
  println(toType(1) =:= typeOf[AnyVal]) //false
  println(toType(1) =:= toType(1))      // true
  println(toType(1) =:= toType(true))   // false
  println(toType(1) <:< typeOf[AnyVal]) // true
  println(toType(1) <:< toType(1))      // true
  println(toType(1) <:< toType(true))   // false
  println(typeOf[Seq[Int]] =:= typeOf[Seq[Any]]) // false
  println(typeOf[Seq[Int]] <:< typeOf[Seq[Any]]) // true

  //获取类型目录
  println(typeTag[Int])
  println(typeTag[Seq[Int]])

  //通过TypeRef获取更多类型信息
  def toTypeRefInfo[T:TypeTag](x:T): (universe.Type, universe.Symbol, List[universe.Type]) ={
    val TypeRef(pre,typeName,param) = toType(x)
    (pre,typeName,param)
  }

  println(toTypeRefInfo(1))
  println(toTypeRefInfo(true))
  println(toTypeRefInfo(Seq(1, true, 3.14)))
  println(toTypeRefInfo((i: Int) => i.toString))

  //通过TypeApi获取更多信息
  val ts = toType(Seq(1, true, 3.14))
  println(ts.typeSymbol)
  println(ts.erasure)
  println(ts.typeArgs)
  println(ts.baseClasses)
  println(ts.companion)
  println(ts.decls)//获取它直接声明的成员
  println(ts.members)//获取所有成员，包括继承来的
}

package com.github.zjjfly.ps.chapter24

import java.lang.reflect.TypeVariable

/**
  * Created by zjjfly on 2017/6/5.
  */
object Reflect extends App {
  val c = new C(3)
  val clazz = classOf[C]
  val clazz2 = c.getClass
  val name = clazz.getName
  val methods = clazz.getMethods
  val ctors = clazz.getConstructors
  val fields = clazz.getFields
  val annos = clazz.getAnnotations
  val parentInterfaces = clazz.getInterfaces
  val superClass = clazz.getSuperclass
  val typeParameters: Array[TypeVariable[Class[C]]] = clazz.getTypeParameters
  //Scala不鼓励使用下面两个方法，使用模式匹配会更好
  assert(c.isInstanceOf[C])
  c.asInstanceOf[T[AnyRef]]
}

trait T[A] {
  val vT: A

  def mT = vT
}

class C(foo: Int) extends T[String] {
   val vT="T"
   val vC="C"
   def mC=vC
   class C2
}

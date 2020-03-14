package com.github.zjjfly.ps.chapter22

import scala.beans.BeanProperty

/**
  * Created by zjjfly on 2017/6/4.
  */
case class ComplexBean(@BeanProperty real: Double,
                       @BeanProperty imaginary: Double)

object ComplexBean {
  def main(args: Array[String]): Unit = {
    val bean = ComplexBean(1.1, 1.0)
    println(bean.getReal())
  }
}

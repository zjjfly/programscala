package com.github.zjjfly.ps.Chapter3

/**
  * Created by zjjfly on 2016/11/21.
  */
object InterpolatedString {
  def main(args: Array[String]): Unit = {
    //最普通的可插入字符串
    val name = "jjzi"
    println(s"Hello,$name")
    //还有两种可插入字符串,一种是printf,还有一种是raw可插入字符串
    val gross = 100000F
    val net = 64000F
    val percent = (net / gross) * 100
    //printf
    println(f"$$${gross}%.2f vs.$$${net}%.2f or ${percent}%.1f%%")
    //stringLike中有一些额外的操作String的方法,其中有一个format函数,传入需要被插入到字符串中的参数
    val s = "%02d: name = %s".format(5, "Dean Wampler")
    println(s)
    //raw
    println(raw"123\n$name\n456")
  }
}

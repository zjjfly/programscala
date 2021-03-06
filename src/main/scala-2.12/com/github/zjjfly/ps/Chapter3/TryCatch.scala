package com.github.zjjfly.ps.Chapter3

import scala.util.control.NonFatal

/**
  * Created by zjjfly on 2016/11/6.
  */
object TryCatch {
  def main(args: Array[String]) = {
    args foreach (arg => countLines(arg)) //
  }

  import scala.io.Source

  // import scala.util.control.NonFatal
  def countLines(fileName: String) = {
    // println() // Add a blank line for legibility
    var source: Option[Source] = None //
    try {
      source = Some(Source.fromFile(fileName))
      val size = source.get.getLines.size
      println(s"file $fileName has $size lines")
    } catch {
      case NonFatal(ex) => println(s"Non fatal exception! $ex") //
    } finally {
      for (s <- source) {
        //这是一种常用的对Option对象进行操作的方法
        //for句式只有当Option中有值的时候才会执行后面语句块
        println(s"Closing $fileName...")
        s.close
      }
    }
  }
}

package com.github.zjjfly.ps.chapter17

/**
  * Created by zjjfly on 2017/6/2.
  */
import java.io.File
import java.net.URL

import scala.sys.process._
object Processes extends App {
  //执行命令，返回执行结果
  println("ls -l src".!)
  //执行命令，并输出
  println(Seq("ls", "-l", "src").!!)

  def findURL(url: String, filter: String): ProcessBuilder = {
    new URL(url) #> s"grep $filter" #>> new File(s"$filter.txt") //#>类似shell中的<,#>>类似>>
  }

  def countLines(fileName: String): ProcessBuilder = {
    s"ls -l $fileName" #&& s"wc -l $fileName" //#&&表示如果左边的处理执行成功，那么继续执行右边的处理
  }
  findURL("http://scala-lang.org/", "scala").!
  countLines("scala.txt").!
}

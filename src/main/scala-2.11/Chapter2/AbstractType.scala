package Chapter2

import java.io.File

import scala.io.Source

/**
  * Created by zjjfly on 2016/11/2.
  */
object AbstractType {
  def main(args: Array[String]): Unit = {
    println(new StringBulkReader("hello").read)
    println(new FileBulkReader(new File("2.txt")).read)
  }
}
abstract class BulkReader{
  type In//虚类型,作用和参数化类型有重叠,但有各自的优势和缺点
  val source:In
  def read:String
}
class StringBulkReader(val source: String) extends BulkReader{
  type In=String

  override def read: String = source
}
class FileBulkReader(val source: File) extends BulkReader{
  override type In = File

  override def read: String = Source.fromFile(source).getLines().mkString
}
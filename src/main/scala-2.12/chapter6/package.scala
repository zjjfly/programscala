/**
  * Created by zjjfly on 2017/3/25.
  */
package object chapter6 {
  type Seq[+A]=scala.collection.immutable.Seq[A]//修改Seq对应的类型。默认的是scala.collection.Seq，在scala包的包对象中声明的
  val Seq=scala.collection.immutable.Seq//导入Seq伴生对象
}

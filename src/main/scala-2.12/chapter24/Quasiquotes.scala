package chapter24

/**
  * Created by zjjfly on 2017/6/6.
  */
object Quasiquotes extends App {

  import reflect.runtime.universe._

  val C = q"case class C(s:String)"
  println(C)
  println(showCode(C)) //打印原始的声明语法
  println(showRaw(C))
  //打印类型树
  val q = q"List[String]"
  val tq = tq"List[String]"
  println(showRaw(q))
  println(showRaw(tq))
  assert(!(q equalsStructure tq))
  Seq(tq"Int", tq"String") map { param =>
    q"case class C(s: $param)"
  } foreach { q =>
    println(showCode(q))
  }

  val list = Seq(1,2,3,4)

  val fmt = "%d, %d, %d, %d"

  val printq = q"println($fmt, ..$list)"//..$list会把list展开成逗号分隔的值
  println(printq)//println("%d, %d, %d, %d", 1, 2, 3, 4)
  val q"${i:Int} + ${d:Double}"=q"1 + 3.14"
  println(i+","+d)

}

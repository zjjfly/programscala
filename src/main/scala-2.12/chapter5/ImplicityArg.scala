package chapter5

/**
  * Created by zjjfly on 2017/1/21.
  */
object ImplicityArg {
  def main(args: Array[String]): Unit = {
    val list: MyList[Int] = MyList(List(2,4,5,6))
    println(list.sortBy1(_+1))
    println(list.sortBy2(_+1))
  }
}
case class MyList[A](list:List[A]){
  def sortBy1[B](f:A=>B)(implicit ord:Ordering[B]):List[A]=
    list.sortBy(f)(ord)
  def sortBy2[B:Ordering](f:A=>B):List[A]=
    list.sortBy(f)(implicitly[Ordering[B]])
}

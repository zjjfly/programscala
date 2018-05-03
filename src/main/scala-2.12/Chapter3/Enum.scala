package Chapter3

/**
  * Created by zjjfly on 2016/11/21.
  */
object Enum {
  def main(args: Array[String]): Unit = {
    println("ID\treed")
    for (breed <- Breed.values) println(s"${breed.id}\t$breed")
    println("\nJust Terriers:")
    Breed.values filter (_.toString.endsWith("Terrier")) foreach println
    println("\nTerriers Again??")
    Breed.values filter isTerrier foreach println
    WeekDay.values filter isworkingDay foreach println
  }
  import Breed._
  def isTerrier(b: Breed) = b.toString.endsWith("Terrier")
  import WeekDay._
  def isworkingDay(d: WeekDay) = !(d == Sat || d == Sun)
}
//scala的枚举通过继承Enumeration实现
//但其实scala很少使用这个特性,更多的使用case class来替代
object Breed extends Enumeration {
  //这句必须有,不然编译会出错
  type Breed = Value
  val doberman = Value("Doberman Pinscher")
  val yorkie = Value("Yorkshire Terrier")
  val scottie = Value("Scottish Terrier")
  val dane = Value("Great Dane")
  val portie = Value("Portuguese Water Dog")
}
object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}

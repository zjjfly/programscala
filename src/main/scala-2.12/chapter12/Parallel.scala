package chapter12

/**
  * Created by zjjfly on 2017/4/28.
  */
object Parallel extends App {
  val s1 = (1 to 10 fold "")((s1, s2) => s"$s1 - $s2")
  val s2 = (1 to 10 fold "")((s1, s2) => s"$s1 - $s2")
  println(s1)
  println(s2)
  val p1 = (1 to 10).par
    .fold("")((s1, s2) => s"$s1 - $s2")
  val p2 = (1 to 10).par
    .fold("")((s1, s2) => s"$s1 - $s2")
  println(p1)
  assert((1 to 10).sum == 55)
  println(p2)
  assert((1 to 10).sum == 55)
  assert((1 to 10).par.sum == 55)
  assert((1 to 10).par.sum == 55)
}

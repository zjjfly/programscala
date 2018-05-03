package chapter8

/**
  * Created by zjjfly on 2017/4/15.
  */
object UnaryMethod extends App {

  case class Complex(real: BigDecimal, imag: BigDecimal) {
    def unary_- : Complex = Complex(-real, imag)

    def -(other: Complex) = Complex(real - other.real, imag - other.imag)
  }

  val c1 = Complex(1.1, 2.2)
  val c2 = -c1
  assert(c2 == Complex(-1.1, 2.2))
  val c3 = c1.unary_-
  assert(c3 == c2)
  val c4 = c1 - Complex(0.5, 1.0)
  println(s"c4 = $c4")
  assert(c4 == Complex(0.6, 1.2))
}

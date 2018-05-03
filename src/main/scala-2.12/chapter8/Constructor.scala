package chapter8

/**
  * Created by zjjfly on 2017/4/14.
  */
object Constructor extends App {

  case class Address(street: String, city: String, state: String, zip: String) {
    def this(zip: String) =
      this("[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)
  }

  object Address {
    def zipToCity(zip: String) = "Anytown"
    def zipToState(zip: String) = "CA"
  }

  //使用带名参数和可选参数，重载apply方法，比使用辅助构造器更好
  case class Person3(name: String,
                     age: Option[Int] = None,
                     address: Option[Address] = None)

  object Person3 {
    def apply(name: String): Person3 = new Person3(name)

    def apply(name: String, age: Int): Person3 = new Person3(name, Some(age))

    def apply(name: String, age: Int, address: Address): Person3 =
      new Person3(name, Some(age), Some(address))

    def apply(name: String, address: Address): Person3 =
      new Person3(name, address = Some(address))
  }

}

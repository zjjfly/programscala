package chapter8

import scala.collection.immutable.HashMap

/**
  * Created by zjjfly on 2017/4/15.
  */
object NestedTypes extends App {

  object Database {

    case class ResultSet(/*...*/)

    case class Connection(/*...*/)

    case class DatabaseException(message: String, cause: Throwable) extends RuntimeException(message, cause)

    sealed trait Status

    case object Disconnected extends Status//当case类没有任何字段代表额外的状态信息时，考虑使用case object

    case class Connected(connection: Connection) extends Status

    case class QuerySucceeded(results: ResultSet) extends Status

    case class QueryFailed(e: DatabaseException) extends Status

  }

  class Database {

    import Database._

    def connect(server: String): Status = ??? //???实际上是一个方法，定义在Predef中。它只是简单的抛出一个异常，用于标记方法还没有实现

    def disconnect(): Status = ???

    def query(/*...*/): Status = ???
  }

  case object Foo

  assert(Foo.hashCode() == "Foo".hashCode)

}

package chapter5

/**
  * Created by zjjfly on 2017/1/29.
  */
package database_api
case class InvalidColumnName(name: String)
    extends RuntimeException(s"Invalid column name $name")

trait Row {
  def getInt(colName: String): Int
  def getDouble(colName: String): Double
  def getText(colName: String): String
}
package javadb {

  case class JRow(representation: Map[String, Any]) extends Row {

    private def get(colName: String): Any =
      representation.getOrElse(colName, throw InvalidColumnName(colName))

    override def getInt(colName: String): Int = get(colName).asInstanceOf[Int]

    override def getDouble(colName: String): Double =
      get(colName).asInstanceOf[Double]

    override def getText(colName: String): String =
      get(colName).asInstanceOf[String]
  }

  object JRow {
    def apply(pairs: (String, Any)*): JRow = new JRow(Map(pairs: _*))
  }
}

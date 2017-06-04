package chapter19

/**
  * Created by zjjfly on 2017/6/4.
  */
case class CLINQ[T](records: Seq[Map[String, T]]) extends Dynamic {
  def selectDynamic(name: String): CLINQ[T] = {
    if (name == "all" || records.isEmpty) this
    else {
      val fields = name.split("_and_")
      val seed = Seq.empty[Map[String, T]]
      val newRecords = (records foldLeft seed) {
        (results, record) =>
          val projection = record.filter {
                                           case (key, value) => fields contains key
                                         }
          if (projection.size > 0) results :+ projection
          else results
      }
      CLINQ(newRecords)
    }
  }

  def applyDynamic(name: String)(field: String): Where = name match {
    case "where" => new Where(field)
    case _ => throw CLINQ.BadOperation(field, """Expected "where".""")
  }

  protected class Where(field: String) extends Dynamic {
    def filter(value: T)(op: (T, T) => Boolean): CLINQ[T] = {
      val newRecords = records filter {
        _ exists {
          case (k, v) => field == k && op(value, v)
        }
      }
      CLINQ(newRecords)
    }

    def applyDynamic(op: String)(value: T): CLINQ[T] = op match {
      case "EQ" => filter(value)(_ == _) //
      case "NE" => filter(value)(_ != _) //
      case _ => throw CLINQ.BadOperation(field, """Expected "EQ" or "NE".""")
    }

    override def toString: String = records mkString "\n"
  }

}

object CLINQ {

  case class BadOperation(name: String, msg: String) extends RuntimeException(
    s"Unrecognized operation $name. $msg")

  def main(args: Array[String]): Unit = {
    def makeMap(name: String, capital: String, statehood: Int): Map[String, Any] = Map("name" -> name, "capital" -> capital,
                                                                                       "statehood" -> statehood)

    val states = CLINQ(List(
      makeMap("Alaska", "Juneau", 1959),
      makeMap("California", "Sacramento", 1850),
      makeMap("Illinois", "Springfield", 1818),
      makeMap("Virginia", "Richmond", 1788),
      makeMap("Washington", "Olympia", 1889)))
    println(states.name)
    println(states.capital)
    println(states.statehood)
    println(states.name_and_capital)
    println(states.all)
    println(states.all.where("name").NE("Alaska"))
    println(states.all.where("statehood").EQ(1889))
    println(states.name_and_statehood.where("statehood").NE(1850))
  }
}
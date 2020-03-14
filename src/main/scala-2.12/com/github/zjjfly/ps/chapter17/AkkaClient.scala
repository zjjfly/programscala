package com.github.zjjfly.ps.chapter17

import akka.actor.{ActorRef, ActorSystem}

/**
  * Created by zjjfly on 2017/6/3.
  */
object AkkaClient {

  import Message._

  private var system: Option[ActorSystem] = None

  private def processArg(args: Seq[String]): Unit = args match {
    case Nil                       =>
    case ("--help" | "-h") +: tail => exit(help, 0)
    case head +: tail              => exit(s"Unknown input $head!\n" + help, 1)
  }

  private val help = // <19>
    """Usage: AkkaClient [-h | --help]
      |Then, enter one of the following commands, one per line:
      |  h | help      Print this help message.
      |  c n string    Create "record" for key n for value string.
      |  r n           Read record for key n. It's an error if n isn't found.
      |  u n string    Update (or create) record for key n for value string.
      |  d n           Delete record for key n. It's an error if n isn't found.
      |  crash n       "Crash" worker n (to test recovery).
      |  dump [n]      Dump the state of all workers (default) or worker n.
      |  q | quit | exit    Quit.
      |""".stripMargin

  private def exit(message: String, status: Int): Nothing = { // <20>
    for (sys <- system) sys.terminate()
    println(message)
    sys.exit(status)
  }

  def main(args: Array[String]): Unit = {
    processArg(args)
    val sys = ActorSystem("AkkaClient")
    system = Some(sys)
    val numberOfWorkers = sys.settings.config.getInt("server.number-workers")
    val server = ServerActor.make(sys)
    server ! Start(numberOfWorkers)
    processInput(server)
  }

  private def processInput(server: ActorRef): Unit = {
    val blankRE = """^\s*#?\s*$""".r
    val badCrashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s*$""".r
    val crashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s+(\d+)\s*$""".r
    val dumpRE = """^\s*[Dd][Uu][Mm][Pp](\s+\d+)?\s*$""".r
    val charNumberRE = """^\s*(\w)\s+(\d+)\s*$""".r
    val charNumberStringRE = """^\s*(\w)\s+(\d+)\s+(.*)$""".r

    def prompt() = print(">> ")

    def missingActorNumber() =
      println("Crash command requires an actor number.")

    def invalidInput(s: String) = println(s"Unrecognized command: $s")

    def invalidCommand(c: String): Unit =
      println(s"Expected 'c', 'r', 'u', or 'd'. Got $c")

    def expectedString(): Unit =
      println("Expected a string after the command and number")

    def unexpectedString(c: String, n: Int): Unit =
      println(s"Extra arguments after command and number '$c $n'")

    def finished(): Nothing = exit("Goodbye!", 0)

    val handleLine: PartialFunction[String, Unit] = {
      case blankRE()    =>
      case "h" | "help" => println(help)
      case dumpRE(n) => //
        server ! (if (n == null) DumpAll else Dump(n.trim.toInt))
      case badCrashRE() => missingActorNumber()
      case crashRE(n)   => server ! Crash(n.toInt)
      case charNumberStringRE(c, n, s) =>
        c match {
          case "c" | "C" => server ! Create(n.toInt, s)
          case "u" | "U" => server ! Update(n.toInt, s)
          case "r" | "R" => unexpectedString(c, n.toInt)
          case "d" | "D" => unexpectedString(c, n.toInt)
          case _         => invalidCommand(c)
        }
      case charNumberRE(c, n) =>
        c match {
          case "r" | "R" => server ! Read(n.toInt)
          case "d" | "D" => server ! Delete(n.toInt)
          case "c" | "C" => expectedString()
          case "u" | "U" => expectedString()
          case _         => invalidCommand(c)
        }
      case "q" | "quit" | "exit" => finished()
      case string                => invalidInput(string)
    }
    while (true) {
      prompt()
      Console.in.readLine() match {
        case null => finished()
        case line => handleLine(line)
      }
    }
  }
}

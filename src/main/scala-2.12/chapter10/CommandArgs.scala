package chapter10

/**
  * Created by zjjfly on 2017/4/19.
  */
object CommandArgs {
  val help =
    """
      |usage: java ... objectsystem.CommandArgs arguments |where the allowed arguments are:
      |  -h | --help
      |  -i | --in | --input path
      |  -o | --on | --output path
      |""".stripMargin

  def quit(message: String, status: Int): Nothing = {
    if (message.length > 0) println(message)
    println(help)
    sys.exit(status)
  }

  case class Args(inputPath: String, outputPath: String)

  def parseArgs(args: Array[String]): Args = {
    def pa(args2: List[String], result: Args): Args = args2 match {
      case Nil => result
      case ("-h" | "--help") :: Nil => quit("", 0)
      case ("-i" | "--in") :: path :: tail => pa(tail, result.copy(inputPath = path))
      case ("-o" | "--out") :: path :: tail => pa(tail, result.copy(outputPath = path))
      case _=>quit(s"Unrecognized argument ${args2.head}",1)
    }
    val argz: Args =pa(args.toList,Args("",""))
    if(argz.inputPath==""||argz.outputPath=="")
      quit("Must specify input and output paths",1)
    argz
  }

  def main(args: Array[String]): Unit = {
    val argz=parseArgs(args)
    println(argz)
  }

}

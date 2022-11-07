name := "program-scala"

version := "1.0"

scalaVersion := "2.12.7"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "com.typesafe.akka" % "akka-slf4j_2.12" % "2.4.16",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1", //最后的test表示scope，说明只在测试代码中使用
  "com.typesafe.play" %% "play-json" % "2.6.0-M1",
  "org.scalaz" %% "scalaz-core" % "7.2.10",
  "org.scala-lang.modules" % "scala-async_2.12" % "0.9.6",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "junit" % "junit" % "4.12",
  "org.scala-lang" % "scala-reflect" % "2.12.1"
)

scalacOptions ++= Seq("-Xlint", "-language:reflectiveCalls", "-language:implicitConversions", "-language:postfixOps",
                      "-language:higherKinds", "-language:dynamics", "-feature", "-deprecation", "-unchecked")

name := "ProgramScala"

version := "1.0"

scalaVersion := "2.12.1"


libraryDependencies +="com.typesafe.akka" %% "akka-actor" % "2.4.16"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"//最后的test表示scope，说明只在测试代码中使用
//scalacOptions +="-Xlint"
scalacOptions +="-language:reflectiveCalls"
scalacOptions +="-language:implicitConversions"
scalacOptions +="-feature"
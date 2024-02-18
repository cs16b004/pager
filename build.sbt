
ThisBuild / scalaVersion :="2.13.12"

val circeVersion = "0.14.1"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies ++= Seq(
 "io.circe" %% "circe-core",
 "io.circe" %% "circe-generic",
 "io.circe" %% "circe-parser"
).map(_ % circeVersion)
//libraryDependencies += "org.pegdown" % "pegdown" % "1.4.2"
libraryDependencies += "io.circe" %% "circe-yaml" % "0.14.2"
libraryDependencies += "org.parboiled" % "parboiled-java" % "1.1.6"
libraryDependencies += "org.planet42" %% "laika-pdf" % "0.19.3"
libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.7.1"
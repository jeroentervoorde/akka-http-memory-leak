name          := """akka-http-memory-leak"""
version       := "0.1.SNAPSHOT"
scalaVersion  := "2.11.8"

val akkaVersion = "2.4.11"

libraryDependencies ++= {
  Seq(

    "com.typesafe.akka"		%%  "akka-actor"				  % akkaVersion,
    "com.typesafe.akka"		%%  "akka-http-core"				% akkaVersion,
    "com.typesafe.akka"		%%  "akka-http-experimental"		% akkaVersion,
    "com.typesafe.akka"		%%  "akka-http-spray-json-experimental"	% akkaVersion
  )
}

lazy val root = project.in(file("."))
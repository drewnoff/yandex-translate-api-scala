name := "Yandex Translate API"

version := "0.0.0"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "0.3.12"

libraryDependencies += "play" % "play_2.10" % "2.1.4"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

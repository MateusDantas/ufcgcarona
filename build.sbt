name := """carona"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "javax.inject" % "javax.inject" % "1",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

organization := "com.typesafe"

name := "slick-migrations"

version := "1.0"

scalaVersion := "2.10.0"

scalacOptions += "-deprecation"

scalacOptions += "-feature"

libraryDependencies ++= List(
  "com.typesafe.slick" % "slick_2.10.0" % "1.1.0-SNAPSHOT"
  , "com.typesafe" % "config" % "1.0.1"
  ,"org.scala-lang" % "scala-compiler" % "2.10.0"
  ,"com.h2database" % "h2" % "1.3.166"
  ,"org.xerial" % "sqlite-jdbc" % "3.6.20"
  ,"org.slf4j" % "slf4j-nop" % "1.6.4" // <- disables logging
  ,"mysql" % "mysql-connector-java" % "5.1.13"
  /*
  // Other database drivers
  ,"org.apache.derby" % "derby" % "10.6.1.0"
  ,"org.hsqldb" % "hsqldb" % "2.0.0"
  ,"postgresql" % "postgresql" % "8.4-701.jdbc4"
  */
)
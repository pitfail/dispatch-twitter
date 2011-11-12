
resolvers += "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"

scalaVersion := "2.10.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "net.databinder" % "dispatch-core_2.9.1" % "0.8.5",
  "net.databinder" % "dispatch-http-json_2.9.1" % "0.8.5",
  "net.databinder" % "dispatch-lift-json_2.9.1" % "0.8.5",
  "net.databinder" % "dispatch-oauth_2.9.1" % "0.8.5",
  "org.scala-tools.testing" % "specs_2.9.1"      % "1.6.9"  % "test->default"
)


enablePlugins(ScalaJSPlugin)

name := "Scala.js Playground"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.0",
  "org.singlespaced" %%% "scalajs-d3" % "0.3.1"
)

persistLauncher in Compile := true

persistLauncher in Test := false
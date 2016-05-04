lazy val root = (project in file(".")).
  enablePlugins(ScalaJSPlugin).
  settings(
    name := "Scala.js Playground",
    scalaVersion := "2.11.8",
    version := "1.0"
  )

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.0",
  "com.lihaoyi" %%% "scalatags" % "0.5.4",
  "org.singlespaced" %%% "scalajs-d3" % "0.3.3",
  "com.lihaoyi" %%% "upickle" % "0.4.0"
)

persistLauncher in Compile := true

persistLauncher in Test := false

mainClass in Compile := Some("ch.netzwerg.RestClient")
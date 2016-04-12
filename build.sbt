enablePlugins(ScalaJSPlugin)

name := "Scala.js Playground"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.thoughtworks.binding" %%% "dom" % "4.0.1",
  "org.scala-lang" % "scala-reflect" % "2.11.8" // explicit dependency to fix version collision warning
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

persistLauncher in Compile := true

persistLauncher in Test := false
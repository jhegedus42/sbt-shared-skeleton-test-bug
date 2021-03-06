import sbt.Keys._
import sbt.Project.projectToRef
import webscalajs.SourceMappings

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")) .settings(
    scalaVersion := Settings.versions.scala,
    libraryDependencies ++= Settings.sharedDependencies.value
) 

lazy val sharedJVM = shared.jvm.settings(name := "sharedJVM")

lazy val sharedJS = shared.js.settings(name := "sharedJS")


lazy val client: Project = (project in file("client"))
  .settings(
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.scalajsDependencies.value,
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(sharedJS)

lazy val clients = Seq(client)

lazy val server = (project in file("server")) .settings(
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value
  )
  .enablePlugins(SbtLess,SbtWeb)
  .aggregate(clients.map(projectToRef): _*)
  .dependsOn(sharedJVM)

onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value




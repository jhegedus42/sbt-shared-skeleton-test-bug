import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._

object Settings {
  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-Yrangepos"
  )

  object versions {
    val scala = "2.11.8"
  }

   val scalaTest=Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
     "org.specs2" %% "specs2" % "3.7" % "test")

   val sharedDependencies = Def.setting(Seq(
    "com.lihaoyi" %%% "autowire" % "0.2.6"
  )++scalaTest)

 val jvmDependencies = Def.setting(Seq(
   "org.scalaz" %% "scalaz-core" % "7.2.8"
 ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1"
  )++scalaTest)
}

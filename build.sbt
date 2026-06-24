
import sbt.Keys.libraryDependencies
import sbt.Resolver

/** Settings shared globally. **/
lazy val commonSettings = Seq(
  organization := "org.combinators",

  scalaVersion := "3.8.4",

  resolvers += Resolver.typesafeRepo("releases"),
  resolvers += Resolver.mavenCentral,

  Compile/scalacOptions ++= Seq(
    "-explain",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-Xkind-projector:underscores",
  ),

  libraryDependencies ++= Seq(
    "commons-io"% "commons-io" % "2.22.0",
    "org.combinators" %% "templating" % "1.1.5",
    "org.scalactic" %% "scalactic" % "3.2.20" % "test",
    "org.scalatest" %% "scalatest" % "3.2.20" % "test",
    "org.typelevel" %% "cats-core" % "2.13.0",
    "org.typelevel" %% "cats-free" % "2.13.0",
    "org.typelevel" %% "cats-effect" % "3.7.0"

),
  evictionErrorLevel := Level.Info,
)

lazy val root = (project in file("."))
  .aggregate(dynamicProgramming) // Commands run on root will trickle down to these modules
  .settings(
    commonSettings,
    name := "root",
    version := "0.1.0-SNAPSHOT"
  )

// Define the 'core' submodule mapped to the './dynamicProgramming' directory
lazy val dynamicProgramming = (project in file("dynamicProgramming"))
  .settings(
    commonSettings,
    name := "dynamicProgramming",
    libraryDependencies += "org.combinators" % "expression-problem-language-newScala_3" % "1.0.5",
    libraryDependencies += "org.combinators" % "expression-problem-language-java_3" % "1.0.5",
  )
organization := "dev.travisbrown"
moduleName := "sized-core"
description := "Statically-sized collections"

scalaVersion := "2.13.1"
crossScalaVersions := Seq(scalaVersion.value, "0.22.0-RC1")

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked"
)

scalacOptions ++= (
  if (isDotty.value) Nil
  else
    Seq(
      "-language:existentials",
      "-language:higherKinds",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused:imports"
    )
)

scalacOptions in Test += "-language:implicitConversions"

def macroDependencies(isDotty: Boolean, scalaVersion: String): Seq[ModuleID] =
  if (isDotty) Nil else Seq("org.scala-lang" % "scala-reflect" % scalaVersion % Provided)

def testDependencies(isDotty: Boolean): Seq[ModuleID] =
  if (isDotty) Seq("dev.travisbrown" %% "scalacheck-1-14" % "3.1.0.1-20200201-c4c847f-NIGHTLY" % Test)
  else Seq("org.scalatestplus" %% "scalacheck-1-14" % "3.1.1.1" % Test)

libraryDependencies ++= macroDependencies(isDotty.value, scalaVersion.value)
libraryDependencies ++= testDependencies(isDotty.value)

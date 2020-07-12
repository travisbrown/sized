organization := "dev.travisbrown"
moduleName := "sized-core"
description := "Statically-sized collections"

scalaVersion := "2.13.3"
crossScalaVersions := Seq(scalaVersion.value, "0.24.0")

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

def testDependencies(isDotty: Boolean): Seq[ModuleID] = Seq(
  "org.scalatest" %% "scalatest-funsuite" % "3.2.0" % Test,
  "org.scalatestplus" %% "scalacheck-1-14" % "3.2.0.0" % Test
)

libraryDependencies ++= macroDependencies(isDotty.value, scalaVersion.value)
libraryDependencies ++= testDependencies(isDotty.value)

releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseVcsSign := true
homepage := Some(url("https://github.com/travisbrown/sized"))
licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots".at(nexus + "content/repositories/snapshots"))
  else
    Some("releases".at(nexus + "service/local/staging/deploy/maven2"))
}
autoAPIMappings := true
apiURL := Some(url("https://travisbrown.github.io/sized/api/"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/travisbrown/sized"),
    "scm:git:git@github.com:travisbrown/sized.git"
  )
)
pomExtra := (
  <developers>
    <developer>
      <id>travisbrown</id>
      <name>Travis Brown</name>
      <url>https://twitter.com/travisbrown</url>
    </developer>
  </developers>
)

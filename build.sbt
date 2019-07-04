name := "AecorPractice"

version := "0.1"

scalaVersion := "2.12.0"

val aecorVersion = "0.18.0"

libraryDependencies ++= Seq(
  "io.aecor" %% "core" % aecorVersion,
  "io.aecor" %% "schedule" % aecorVersion,
  "io.aecor" %% "akka-cluster-runtime" % aecorVersion,
  "io.aecor" %% "distributed-processing" % aecorVersion,
  "io.aecor" %% "boopickle-wire-protocol" % aecorVersion,
  "io.aecor" %% "test-kit" % aecorVersion % Test
)
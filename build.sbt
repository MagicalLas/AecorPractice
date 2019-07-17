name := "AecorPractice"

version := "0.1"

scalaVersion := "2.12.0"

val aecorVersion = "0.18.0"
lazy val enumeratumVersion = "1.5.13"

libraryDependencies ++= Seq(
  "io.aecor" %% "core" % aecorVersion,
  "io.aecor" %% "schedule" % aecorVersion,
  "io.aecor" %% "akka-cluster-runtime" % aecorVersion,
  "io.aecor" %% "distributed-processing" % aecorVersion,
  "io.aecor" %% "boopickle-wire-protocol" % aecorVersion,
  "io.aecor" %% "test-kit" % aecorVersion % Test,
  "com.beachape" %% "enumeratum" % enumeratumVersion,
  "com.beachape" %% "enumeratum-circe" % enumeratumVersion
)
resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")

// if your project uses multiple Scala versions, use this for cross building
addCompilerPlugin("org.typelevel" % "kind-projector" % "0.10.3" cross CrossVersion.binary)

// if your project uses both 2.10 and polymorphic lambdas
libraryDependencies ++= (scalaBinaryVersion.value match {
  case "2.10" =>
    compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full) :: Nil
  case _ =>
    Nil
})
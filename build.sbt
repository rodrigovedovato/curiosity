lazy val akkaHttpVersion = "10.1.7"
lazy val akkaVersion    = "2.5.21"
lazy val circe = "0.9.3"
lazy val enumeratumVersion = "1.5.13"
lazy val enumeratumCirceVersion = "1.5.20"
lazy val scalaCacheVersion = "0.27.0"

lazy val root = (project in file(".")).
  settings(
    mainClass in assembly := Some("br.vedovato.Curiosity"),
    assemblyJarName in assembly := "curiosity.jar",
    assemblyMergeStrategy in assembly := {
      case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
      case x => MergeStrategy.defaultMergeStrategy(x)
    },
    inThisBuild(List(
      organization    := "br.vedovato",
      scalaVersion    := "2.12.7",
      version := "0.1.0"
    )),
    name := "curiosity",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,

      "de.heikoseeberger" %% "akka-http-circe" % "1.20.1",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "io.circe" %% "circe-core" % circe,
      "io.circe" %% "circe-generic" % circe,
      "io.circe" %% "circe-parser" % circe,
      "io.circe" %% "circe-derivation" % "0.10.0-M1",
      "com.beachape" %% "enumeratum" % enumeratumVersion,
      "com.beachape" %% "enumeratum-circe" % enumeratumCirceVersion,
      "com.github.cb372" %% "scalacache-core" % scalaCacheVersion,
      "com.github.cb372" %% "scalacache-caffeine" % scalaCacheVersion,

      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.5"         % Test
    )
  )

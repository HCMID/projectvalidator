//name := "MID project validator"

//crossScalaVersions in ThisBuild := Seq("2.11.8", "2.12.4")
//scalaVersion := (crossScalaVersions in ThisBuild).value.last

lazy val supportedScalaVersions = List("2.11.8", "2.12.4")

lazy val root = project.in(file(".")).
    aggregate(crossedJVM, crossedJS).
    settings(
      crossScalaVersions := Nil,
      publish / skip := true
    )


lazy val crossed = crossProject.in(file(".")).
    settings(
      name := "midvalidator",
      organization := "edu.holycross.shot",
      version := "7.0.0",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
      resolvers += Resolver.jcenterRepo,
      resolvers += Resolver.bintrayRepo("neelsmith", "maven"),
      resolvers +=  Resolver.bintrayRepo("cibotech", "public"),

      libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
        "org.scalatest" %%% "scalatest" % "3.0.1" % "test",

        "edu.holycross.shot.cite" %%% "xcite" % "4.1.0",
        "edu.holycross.shot" %%% "ohco2" % "10.13.2",
        "edu.holycross.shot" %%% "citeobj" % "7.3.4",
        "edu.holycross.shot" %%% "citerelations" % "2.5.2",
        "edu.holycross.shot" %%% "dse" % "5.1.2",


        "edu.holycross.shot" %%% "citebinaryimage" % "3.0.0"

        // Later version would bring all dependent libs in sync:
        //"edu.furman.classics" %% "citewriter" % "1.0.2"

      )
    ).
    jvmSettings(
      libraryDependencies ++= Seq(
        "com.github.pathikrit" %% "better-files" % "3.5.0",

        "edu.holycross.shot" %% "scm" % "7.0.1",
        "edu.holycross.shot" %% "cex" % "6.3.3",

        // FOR DEBUGGIN:
        //"edu.holycross.shot" %% "greek" % "2.4.0",
        //"org.homermultitext" %% "hmt-textmodel" % "6.0.1"

      ),
      tutTargetDirectory := file("docs"),
      tutSourceDirectory := file("tut"),
      crossScalaVersions := supportedScalaVersions

    ).
    jsSettings(
      skip in packageJSDependencies := false,
      scalaJSUseMainModuleInitializer in Compile := true,
      crossScalaVersions := supportedScalaVersions

    )

lazy val crossedJVM = crossed.jvm.enablePlugins(TutPlugin)
lazy val crossedJS = crossed.js

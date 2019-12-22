lazy val supportedScalaVersions =  List("2.12.4") // List("2.11.8", "2.12.4")

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
      version := "9.2.0",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
      resolvers += Resolver.jcenterRepo,
      resolvers += Resolver.bintrayRepo("neelsmith", "maven"),


      libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
        "org.scalatest" %%% "scalatest" % "3.0.1" % "test",

        "org.wvlet.airframe" %%% "airframe-log" % "19.8.10",

        "edu.holycross.shot.cite" %%% "xcite" % "4.2.0",
        "edu.holycross.shot" %%% "ohco2" % "10.18.0",
        "edu.holycross.shot" %%% "citeobj" % "7.4.0",
        "edu.holycross.shot" %%% "citerelations" % "2.6.0",
        "edu.holycross.shot" %%% "dse" % "5.3.0",

        "edu.holycross.shot" %%% "histoutils" % "2.2.0",
        "edu.holycross.shot" %%% "citebinaryimage" % "3.1.1"


        // Later version would bring all dependent libs in sync:
        //"edu.furman.classics" %% "citewriter" % "1.0.2"

      )
    ).
    jvmSettings(
      libraryDependencies ++= Seq(
        "com.github.pathikrit" %% "better-files" % "3.5.0",

        "edu.holycross.shot" %% "scm" % "7.0.1",
        "edu.holycross.shot" %% "cex" % "6.3.3",
        "edu.holycross.shot" %% "xmlutils" % "2.0.0"

        // FOR DEBUGGING WITH HMT CONTENT:
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

package edu.holycross.shot.mid.validator

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._


/**
*
* @param validationDir Writable directory for validation reports.
*/
case class ReportWriter(validationDir: File)  {
  require(validationDir.exists, "Valdiation directory not found: " + validationDir)


  def writeReport(reportPage: ReportPage) : Unit = {
    //println("TItle is " + reportPage.title)
    //println("File name " + reportPage.suggestedFileName)
    //println("Write this markdown content:\n" + reportPage.markdown)

    val outFile = validationDir/reportPage.suggestedFileName
    outFile.overwrite(reportPage.markdown)
    println(s"\nReport '${reportPage.title.trim}' written to file:\n==>" + outFile + "\n")

  }


  def writeReports(reportPages: Vector[ReportPage]): Unit = {
    for (reptPage <- reportPages) {
      writeReport(reptPage)
    }
  }

/*
val baseDir = File(s"validation/${pg.collection}-${pg.objectComponent}")
  //val fName = e3urn.collection + "-" + e3urn.objectComponent + "-" + msBurn.collection+ "-" + msBurn.objectComponent + ".md"
  val reptName = "place-names.md"
  val outFile = baseDir/reptName
  outFile.overwrite(rept.toString)
  */

}

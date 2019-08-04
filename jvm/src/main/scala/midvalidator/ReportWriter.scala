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


  /** Write markdown content of a single [[ReportPage]] to
  * file using suggested name.
  *
  * @param reportPage [[ReportPage]] to write to file.
  */
  def writeReport(reportPage: ReportPage) : Unit = {
    //println("TItle is " + reportPage.title)
    //println("File name " + reportPage.suggestedFileName)
    //println("Write this markdown content:\n" + reportPage.markdown)

    val outFile = validationDir/reportPage.suggestedFileName
    outFile.overwrite(reportPage.markdown)
    println(s"\nReport '${reportPage.title.trim}' written to file:\n==>" + outFile + "\n")

  }

  /** Write markdown content of a series of [[ReportPage]]s to
  * files using suggested names.
  *
  * @param reportPages [[ReportPage]]s to write to file.
  */
  def writeReports(reportPages: Vector[ReportPage]): Unit = {
    for (reptPage <- reportPages) {
      writeReport(reptPage)
    }
  }

}

package edu.holycross.shot.mid.validator

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._


/**
*/
case class ReportWriter(baseDir: String)  {

  /** Writable directory for validation reports. */
  val validationDir = File(baseDir + "/validation")
  require(validationDir.exists, "Repository not correctly laid out: missing directory " + validationDir)


  def writeReport(reportPage: ReportPage) : Unit = {
    println("TItle is " + reportPage.title)
    println("File name " + reportPage.suggestedFileName)
    println("Write this markdown content:\n" + reportPage.markdown)
  }

}

package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import scala.scalajs.js.annotation._



/**
*/
@JSExportAll case class DseOverview(lib: CiteLibrary) extends ReportOverview {
  lazy val dse = DseVector.fromCiteLibrary(lib)
  lazy val corpus = lib.textRepository.get.corpus
  lazy val dseResults :  DseResults[DsePassage] = DseResults(corpus)

  def failures: Int = {
    dseResults.failures(dse.passages)
  }
  def successes: Int = {
    dseResults.successes(dse.passages)
  }
  def reportPages =  {
    Vector(overviewPage, transcriptionPage)
  }

  def overviewPage: ReportPage = {
    def markdown: String = s"Successful tests: ${successes}\n\nFailed tests: ${failures}\n"
    def suggestedFileName: String = "dse-summary.md"
    def title: String = "## Dse relations: summary\n\n"
    ReportPage(title, markdown, suggestedFileName)
  }

  def transcriptionPage: ReportPage = {
    def markdown: String = s"Beautiful transcription viz goes here\n"
    def suggestedFileName: String = "transcription.md"
    def title: String = "## Verify transcription\n\n"
    ReportPage(title, markdown, suggestedFileName)
  }



}

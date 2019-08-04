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

  def failures(surface: Cite2Urn): Int = {
    //dseResults.failures(dse.passages)
    pageReports(surface).filter(_.success == false).size
  }
  def successes(surface: Cite2Urn): Int = {
    //dseResults.successes(dse.passages)
    pageReports(surface).filter(_.success).size
  }

  def reportPages(surface: Cite2Urn) =  {
    Vector(overviewPage(surface), transcriptionPage(surface))
  }

  def pageDse(surface: Cite2Urn): Vector[DsePassage] = {
    dse.passages.filter(_.surface == surface)
  }

  def pageReports(surface: Cite2Urn) = {
    dseResults.reports(pageDse(surface))
  }
/*
  def pageSuccesses(surface: Cite2Urn) : Int = {
    pageReports(surface).filter(_.success).size
  }
  def pageFailures(surface: Cite2Urn) : Int = {
    pageReports(surface).filter(_.success == false).size
  }*/

  def overviewPage(surface: Cite2Urn): ReportPage = {
    def markdown: String = s"Successful tests: ${successes}\n\nFailed tests: ${failures}\n"
    def suggestedFileName: String = "dse-summary.md"
    def title: String = s"Dse relations, ${surface}: summary\n\n"
    ReportPage(title, markdown, suggestedFileName)
  }

  def transcriptionPage(surface: Cite2Urn): ReportPage = {
    def markdown: String = s"Beautiful transcription viz goes here\n"
    def suggestedFileName: String = "transcription.md"
    def title: String = "## Verify transcription\n\n"
    ReportPage(title, markdown, suggestedFileName)
  }




/*

    md.append(s"# Automated validation of DSE records for ${pg.collection}, page " + pg.objectComponent + "\n\n")
    if (dse.size == 0) {
      md.append("## Errors\n\nNo DSE records found!\n\n")
    } else {

      val dseImgMessage = dse.imageForTbs(pg)
      md.append("\n\n## Reference image\n\n" +  dseImgMessage  + "\n\n")

      val dseTextMessage =  if (missingPassages.isEmpty) {
        "**All** passages indexed in DSE records appear in text corpus."

      } else {
        "### Errors\n\nThere were errors indexing texts. \n\n" +
        "The following passages in DSE records do not appear in the text corpus:\n\n" + missingPassages.map("-  " + _ ).mkString("\n") + "\n\n"

      }
      md.append("\n\n## Relation of DSE records to text corpus\n\n" +  dseTextMessage  + "\n\n" + errors.toString + "\n\n")
*/

}

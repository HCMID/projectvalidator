package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._
import edu.holycross.shot.citebinaryimage._

import scala.scalajs.js.annotation._

// In summary page, report on validation details +
// verification of completeness of coverage.
//
// In seeparate page, report on verification of
// correctness of transcription mapping

/** Class for compiling reporting on DSE validation and verification.
*
* @param lib CiteLibrary to use in DSE validation.
*/
@JSExportAll case class DseOverview(
  lib: CiteLibrary,
  readers: Vector[ReadersPairing],
  imageServiceUrlBase: String = "http://www.homermultitext.org/iipsrv?",
  imageServicePathBase: String = "/project/homer/pyramidal/deepzoom/"
) extends ReportOverview {
  lazy val dse = DseVector.fromCiteLibrary(lib)
  lazy val corpus = lib.textRepository.get.corpus

  // consider making this part of ReportOverview trait .. ?
  lazy val testResults :  DseResults[DsePassage] = DseResults(corpus)


  /** Determine MidMarkupReader to use for diplomatic edition
  * of a specified text.
  *
  * @param urn Text to find markup reader for.
  */
  def diplomaticReader(urn: CtsUrn) : MidMarkupReader = {
    val readerMatches = readers.filter(_.urn >= urn)
    require(readerMatches.size == 1, s"Failed to find diplomatic reader.\nURN matched more than one configuration entry: \n\t${urn}")
    readerMatches(0).readers(0)
  }

  /** Number of failed tests for entire library.*/
  def failuresAll: Int = {
    testResults.failures(dse.passages)
  }

  /** Number of successful tests for entire library.*/
  def successesAll: Int = {
    testResults.successes(dse.passages)
  }

  /** Number of failed tests for a specified surface.
  *
  * @param surface Text-bearing surface.
  */
  def failures(surface: Cite2Urn): Int = {
    //pageTestResults(surface).filter(_.success == false).size
    0
  }

  /** Number of successful tests for a specified surface.
  *
  * @param surface Text-bearing surface.
  */
  def successes(surface: Cite2Urn): Int = {
    //pageTestResults(surface).filter(_.success).size
    0
  }

  /** Collection of pages this ReportOverview can compose
  * for a specified surface.
  *
  * @param surface Text-bearing surface.

  def reportPages(surface: Cite2Urn):  Vector[ReportPage] =  {
    Vector(overviewPage(surface), verificationPage(surface))
  }
  */
  /** Vector of all DsePassages occurring on a surface.
  *
  * @param surface Text-bearing surface.
  */
  def pageDse(surface: Cite2Urn): Vector[DsePassage] = {
    dse.passages.filter(_.surface == surface)
  }

  /** Vector of TestResults for all DsePassages occurring on a surface.
  *
  * @param surface Text-bearing surface.

  def pageTestResults(surface: Cite2Urn):  Vector[TestResult] = {
    testResults.reports(pageDse(surface))
  }
  */

  /** Summary or overview page for a given surface.
  *
  * @param surface Text-bearing surface.

  def overviewPage(surface: Cite2Urn): ReportPage = {
    def markdown: String = pageSummary(surface)
    def suggestedFileName: String = s"${surface.collection}-${surface.objectComponent}/dse-validation.md"
    def title: String = s"DSE relations for ${surface.collection}, ${surface.objectComponent}: summary"
    ReportPage(title, markdown, suggestedFileName, Some(successes(surface)), Some(failures(surface)))
  }
  */

  /** Compose markdown page summarizing DSE evaluation
  * for a specified page.
  *
  * @param surface Page to analyze.

  def pageSummary(surface: Cite2Urn) : String = {
    val md = StringBuilder.newBuilder

    val pageFailures = failures(surface)
    md.append ("## Automated validation\n\n")
    md.append(s"Successful tests: ${successes(surface)}\n\nFailed tests: ${pageFailures}\n\n")

    md.append(s">(Compare results for whole corpus:\nSuccessful tests: ${successesAll} / failed tests: ${failuresAll})\n\n")

    if (pageFailures > 0) {
      val testOutput = pageTestResults(surface)
      md.append("\n\n## Errors\n\n")
      for (badResult <- testOutput.filter(_.success == false)) {
        md.append("1. " + badResult.summary + "\n")
      }
    }
    md.toString
  }
  */

  /** Verification page for comparing transcribed text
  * and indexed image segment from DSE record.
  *
  * @param surface Text-bearing surface.

  def verificationPage(surface: Cite2Urn): ReportPage = {
    def markdown: String = verificationView(surface)
    def suggestedFileName: String = s"${surface.collection}-${surface.objectComponent}/dse-verification.md"
    def title: String = s"Verify completeness and correctness for page (surface): ${surface.collection}, ${surface.objectComponent}"
    ReportPage(title, markdown, suggestedFileName, None, None)
  }
  */


  /** Configure an IIIFApi BinaryImageService for the
  * image used as reference image in DSE records for a
  * specified surface.
  *
  * @param surface Text-bearing surface.
  */
  def iiifApiForSurface(surface: Cite2Urn):  IIIFApi = {
    val image = dse.imageForTbs(surface)
    IIIFApi(imageServiceUrlBase, imageServicePathBase + PathUtility.expandedPath(image))
  }


  /** Compose markdown page juxtaposing diplomatic text
  * with indexed image region for human verification.
  *
  * @param surface Text-bearing surface.
  */
  def verificationView(surface: Cite2Urn) : String = {
    val iiif = iiifApiForSurface(surface)

    val md = StringBuilder.newBuilder
    md.append("### Verify completeness\n\n")

    md.append( s"Please verify that all [passages of text on this image](${dse.ictForSurface(surface)}) are indexed.\n\n")

    md.append("### Verify correctness\n\n")
    val relevant = pageDse(surface)

    md.append("Please verify that the diplomatic transcription of the following " + relevant.size + " DSE records corresponds to the associated image.\n\n")

    for ((dsePsg,idx) <- relevant.zipWithIndex) {
      if (testResults.good(dsePsg)) {
        //println("Good to go on " + dsePsg.passage)
        val indexedText = corpus ~~ dsePsg.passage
        val rdr = diplomaticReader(indexedText.nodes(0).urn)
        //diplomaticReader
        val diplomatic = indexedText.nodes.map(n => s"**${idx + 1}**. " + s" (*${n.urn.passageComponent}*) " + rdr.editedNode(n).text )
        md.append("\n\n---\n\n" +  diplomatic.mkString(" ") + " \n")
        md.append("\n\n" + iiif.linkedMarkdownImage(dsePsg.imageroi))

      } else {
        val error = "\n\nError on DSE passage" + dsePsg
        println(error)
        md.append(error)
      }

    }
    md.toString
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

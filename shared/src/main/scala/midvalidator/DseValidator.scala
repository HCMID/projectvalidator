package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

//import scala.reflect.runtime.universe._


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


import scala.scalajs.js.annotation._

@JSExportAll  case class DseValidator(citeLibrary: CiteLibrary,
    baseUrl : String  = "http://www.homermultitext.org/iipsrv?",
    basePath: String = "/project/homer/pyramidal/deepzoom/",
    ictUrl: String = "http://www.homermultitext.org/ict2?"
  ) extends MidValidator[DsePassage] with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)

  def label : String = "Validate DsePassage relations"

  lazy val corpus = citeLibrary.textRepository.get.corpus
  lazy val collections = citeLibrary.collectionRepository.get

  /** Library must implement the DSE model in at least one collection.*/
  lazy val dsev = DseVector.fromCiteLibrary(citeLibrary)

  /** Get ordered list of all text-bearing surfaces in the library.
  */
  def tbs: Vector[Cite2Urn] = {
    val tbsUrn = Cite2Urn("urn:cite2:cite:datamodels.v1:tbsmodel")

    val tbsCollections: Vector[Cite2Urn] = citeLibrary.collectionsForModel(tbsUrn)
    tbsCollections.flatMap (c =>
      (collections ~~ c).map(_.urn)
    )
  }

  /** Validate DSE relations page-by-page for an entire library.
  *
  * @param citeLibrary Library to validate.
  */
  def validate(citeLibrary: CiteLibrary) : Vector[TestResult[DsePassage]]  = {
    tbs.flatMap(validate(_))
  }

  /** Validate DSE content on a single text-bearing surface.
  * Required method for implementation of MidValidator trait.
  *
  * @param surface Validate DSE content on this text-bearing surface.
  */
  def validate(surface: Cite2Urn) : Vector[TestResult[DsePassage]] = {
    info("DSE validate for " + surface + " : start computing DSE...")
    val surfaceDse = dsev.passages.filter(_.surface == surface)
    info("Done. DSE validating " + surfaceDse.size + " DSE passages.")
    for ((dsePsg, count) <- surfaceDse.zipWithIndex) yield {
      info(s"\t${count + 1}/${surfaceDse.size} ${dsePsg.passage}")
      debug("Validating DSE passage " + dsePsg)
      val matches = corpus ~~ dsePsg.passage
      debug(s"Text ${dsePsg.passage} on " + surface + ": " + matches.size + " in corpus")

      val testRes = matches.size match {
        case 0 => TestResult(false, dsePsg.markdown(baseUrl, basePath) + "Indexed passage " + dsePsg.passage + " **NOT FOUND** in text corpus."  , dsePsg)
        case  _ => {
          TestResult(true,  dsePsg.markdown(baseUrl, basePath) + "Text passage " + dsePsg.passage + " found in corpus. " , dsePsg)
        }
      }
      testRes
    }
  }

  /** Composes markdown string for visual verification of a surface.
  *
  * @param surface Surface to verify.
  */
  def verify(surface: Cite2Urn) : String = {
    val surfaceDse = dsev.passages.filter(_.surface == surface)
    val images = surfaceDse.map(dse => "urn=" + dse.imageroi)
    ictUrl + images.mkString("&")
  }


}

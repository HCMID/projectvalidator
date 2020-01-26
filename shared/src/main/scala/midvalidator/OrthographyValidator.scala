package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import scala.reflect.runtime.universe._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

import scala.scalajs.js.annotation._

@JSExportAll  case class OrthographyValidator(citeLibrary: CiteLibrary, orthoMap : Map[CtsUrn, MidOrthography]) extends MidValidator[CtsUrn] with LogSupport {
  def label : String = "Validate orthography"

  /** Lookup MidMarkupReader class by identifying String.
  *
  * @orthoName Name of class implementing MidOrthography trait.
  */
  def orthoForUrn(urn: CtsUrn): MidOrthography = {
    if (orthoMap.keySet.contains(urn)){
      orthoMap(urn)
    } else {
      val msg = "No orthography defined for " + urn
      warn(msg)
      throw (new Exception(msg))
    }
  }

  /** Test an MidToken against an MidOrthography.
  *
  * @param tkn Token to test.
  * @param ortho Orthography to test token against.
  */
  def testToken(tkn : MidToken, ortho: MidOrthography) : TestResult[CtsUrn] = {
    ortho.validString(tkn.string) match {
      case true => TestResult(true, s"${tkn.string} is valid", tkn.urn)
      case false =>   TestResult(false, s"${tkn.string} has invalid code points:  ${ortho.hiliteBadCps(tkn.string)}", tkn.urn)
    }
  }

  def validate(citeLibrary: CiteLibrary) : Vector[TestResult[CtsUrn]]  = {
    val corpus = citeLibrary.textRepository.get.corpus
    val works = corpus.nodes.map(_.urn.dropPassage).distinct
    val testResults = for (wk <- works) yield {
      val subCorpus = corpus ~~ wk
      val ortho  = orthoForUrn(wk)
      val tokens = ortho.tokenizeCorpus(subCorpus)
      tokens.map(testToken(_, ortho))
    }
    testResults.flatten
  }

  /** Required method for implementation of MidValidaor trait.
  *
  * @param surface Validate DSE content on this text-bearing surface.
  */
  def validate(surface: Cite2Urn) : Vector[TestResult[CtsUrn]] = {
    val dsev = DseVector.fromCiteLibrary(citeLibrary)
    val texts = dsev.textsForTbs(surface)
    warn("NOT YET IMPLEMENTED.")
    Vector.empty[TestResult[CtsUrn]]
  }



}

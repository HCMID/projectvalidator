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

@JSExportAll  case class OrthographyValidator(orthoMap : Map[CtsUrn, MidOrthography]) extends MidValidator[CtsUrn] with LogSupport {
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





  def validate(citeLibrary: CiteLibrary) : Vector[TestResult[CtsUrn]]  = {
    val corpus = citeLibrary.textRepository.get.corpus
    val works = corpus.nodes.map(_.urn.dropPassage).distinct
    for (wk <- works) {
      println(orthoForUrn(wk))
    }

    println("VALIDATE " + works)
    // COLLECT ALL
    Vector.empty[TestResult[CtsUrn]]
  }

  /** Required method for implementation of MidValidaor trait.
  *
  * @param surface Validate DSE content on this text-bearing surface.
  */
  def validate(surface: Cite2Urn) : Vector[TestResult[CtsUrn]] = {
    Vector.empty[TestResult[CtsUrn]]
  }

}


  /** Lookup list of MidMarkupReader's by identifying String.
  *
  * @readerName Name of class implementing MidMarkupReader trait.

  def readersForString(readerName: String): Vector[MidMarkupReader] = {
    if (readerMap.keySet.contains(readerName)){
      readerMap(readerName)
    } else {
      throw (new Exception(s"${readerName} is not a recognized MidReader in this project."))
    }
  }
*/
  /** Lookup MidMarkupReader class by identifying String.
  *
  * @orthoName Name of class implementing MidOrthography trait.

  def orthoForString(orthoName: String): MidOrthography = {
    if (orthoMap.keySet.contains(orthoName)){
      orthoMap(orthoName)
    } else {
      throw (new Exception(s"${orthoName} is not a recognized Orthography in this project."))
    }
  }
*/




  /** Validate a series of surfaces.
  def validateOld(surfaceRange: Vector[Cite2Urn]): Unit = {
    for (surface <- surfaceRange)  {
      validate(surface)
    }
  }
*/
  /** Validate a text-bearing surface or surfaces.
  *
  * @param surface Surface to validate.

  def validateOld(surface: Cite2Urn) : Unit = {
    // 1.  determine if urn is a leaf node, container, or range.
    val sad = "Current version of MidValidator does not operate on entire collections, only single surfaces. Unable to process URN " + surface
    info(sad)
    require(surface.objectParts.nonEmpty,sad)


    val sadRange = "Current version of MidValidator does not operate on ranges, only single surfaces. Unable to process URN " + surface
    info(sadRange)
    require(surface.isRange == false, sadRange)


    // 2. For each leaf node:
    //
    // a. DSE validation
    //
    val dsePassageList = dsev.passages.filter(_.surface == surface)
    val dseResults : DseResults[DsePassage] = DseResults(corpus)
    for (dsePsg <- dsePassageList) {
      debug(dseResults.good(dsePsg))
    }
    // b. orthography validation
  }  */

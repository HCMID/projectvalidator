package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

import scala.scalajs.js.annotation._

trait MidValidator[+T]  extends LogSupport {
  /** Label or title for this validator.*/
  def label: String

  /** All [[MidValidator]]s work on a CiteLibrary */
  //def library : CiteLibrary

  /** Short-hand access to library's text corpus.*/
  //lazy val corpus = library.textRepository.get.corpus

  /** Library must implement the DSE model in at least one collection.*/
  //lazy val dsev = DseVector.fromCiteLibrary(library)

  //def validate[T](surface: Cite2Urn) : TestResults[T]
  //def validate(surface: Cite2Urn) : Vector[TestResult[T]]

  def validate(library: CiteLibrary) : Vector[TestResult[T]]

  def successes(library: CiteLibrary): Vector[TestResult[T]] = {
    validate(library).filter(_.success)
  }

  def failures(library: CiteLibrary):  Vector[TestResult[T]]   = {
    validate(library).filterNot(_.success)
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
  }*/

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
  }
    */
}

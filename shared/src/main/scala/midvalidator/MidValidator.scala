package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import scala.scalajs.js.annotation._

@JSExportAll case class MidValidator(
  library: CiteLibrary,
  orthoPairings: Vector[OrthoPairing])
 {

   /** Short-hand access to library's text corpus.*/
   lazy val corpus = library.textRepository.get.corpus


   /** Library must implement the DSE model in at least one collection.*/
   lazy val dsev = DseVector.fromCiteLibrary(library)


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


  /** Validate a series of surfaces.*/
  def validate(surfaceRange: Vector[Cite2Urn]): Unit = {
    for (surface <- surfaceRange)  {
      validate(surface)
    }
  }

  /** Validate a text-bearing surface or surfaces.
  *
  * @param surface Surface to validate.
  */
  def validate(surface: Cite2Urn) : Unit = {
    // 1.  determine if urn is a leaf node, container, or range.
    require(surface.objectParts.nonEmpty,"Current version of DseResults does not operate on entire collections, only single surfaces. Unable to process URN " + surface)

    require(surface.isRange == false, "Current version of DseResults does not operate on ranges, only single surfaces. Unable to process URN " + surface)


    // 2. For each leaf node:
    //
    // a. DSE validation
    //
    val dsePassageList = dsev.passages.filter(_.surface == surface)
    val dseResults : DseResults[DsePassage] = DseResults(corpus)
    for (dsePsg <- dsePassageList) {
      println(dseResults.good(dsePsg))
    }
    // b. orthography validation
  }
}

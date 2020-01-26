package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
//import edu.holycross.shot.dse._
import edu.holycross.shot.scm._


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

import scala.scalajs.js.annotation._

trait MidValidator[+T]  extends LogSupport {
  /** Label or title for this validator.*/
  def label: String

  def validate(library: CiteLibrary) : Vector[TestResult[T]]

  def validate(surface: Cite2Urn) : Vector[TestResult[T]]

  def validate(surfaces: Vector[Cite2Urn]) : Vector[TestResult[T]]  = {
    surfaces.flatMap(validate(_))
  }

  //def markdownResults(results: Vector[TestResult[T]]): String
  def markdownResults[T](results: Vector[TestResult[T]]) : String

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
}

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

@JSExportAll case class LibraryValidator(library : CiteLibrary)  extends LogSupport {
  // Set logging to DEBUG while developing:
  Logger.setDefaultLogLevel(LogLevel.DEBUG)


  /** Collect all validation results for a text-bearing using
  * by apply all [[MidValidator]]s in a given list.
  *
  * @param surface Text-bearing surface to test.
  * @param validators [[MidValidator]]s to apply.
  */
  def validate(surface: Cite2Urn, validators: Vector[MidValidator[Any]]) : Vector[TestResult[Any]] = {

    debug("Applying " + validators.size + " MID Validators to surface " + surface)
    val testGroups = for (validator <- validators) yield {
      validator.validate(surface)
    }
    val allTests = testGroups.flatten
    debug("Total tests: " + allTests.size)
    allTests
  }
}

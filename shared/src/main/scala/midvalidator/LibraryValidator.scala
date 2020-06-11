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

@JSExportAll object LibraryValidator  extends LogSupport {
  // Set logging to DEBUG while developing:
  Logger.setDefaultLogLevel(LogLevel.INFO)


/*
  def validate(surfaces: Vector[Cite2Urn],  validators: Vector[MidValidator[Any]]) : Vector[TestResult[Any]] = {
    val testGroupsByPage = for (surface <- surfaces) yield {
      validate(surface, validators)
    }
    testGroupsByPage.flatten
  }
  */

  def validate(lib: CiteLibrary, validators: Vector[MidValidator[Any]], pg: Cite2Urn) : Vector[TestResult[Any]] = {

    info("Applying " + validators.size + s" MID Validators to page ${pg} in library " + lib.name)
    val testGroups = for (validator <- validators) yield {
      debug("Use " + validator.label)
      validator.validate(pg)
    }
    val allTests = testGroups.flatten
    //debug("Total tests: " + allTests.size)
    allTests
  }




  /** Collect all validation results for a text-bearing using
  * by apply all [[MidValidator]]s in a given list.
  *
  * @param surface Text-bearing surface to test.
  * @param validators [[MidValidator]]s to apply.
  */
  def validate(lib: CiteLibrary, validators: Vector[MidValidator[Any]]) : Vector[TestResult[Any]] = {

    info("Applying " + validators.size + " MID Validators to library " + lib.name)
    val testGroups = for (validator <- validators) yield {
      debug("Use " + validator.label)
      validator.validate(lib)
    }
    val allTests = testGroups.flatten
    //debug("Total tests: " + allTests.size)
    allTests
  }
}

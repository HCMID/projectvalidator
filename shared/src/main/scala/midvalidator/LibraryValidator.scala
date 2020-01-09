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

  def validate(surface: Cite2Urn, validators: Vector[MidValidator[Any]]) : Unit = {

    debug("Applying " + validators.size + " MID Validators to surface " + surface)
    for (validator <- validators) {
      val ress = validator.validate(surface)
      println(ress)
    }
  }
}

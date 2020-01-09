package edu.holycross.shot.mid.validator

import scala.scalajs.js.annotation._
import edu.holycross.shot.cite._

/** Results of testing a single artifact.
*
* @param success True if tested object passed the test.
* @param summary Human-readable summary of the test.
*/
@JSExportAll case class TestResult(success: Boolean, summary: String)

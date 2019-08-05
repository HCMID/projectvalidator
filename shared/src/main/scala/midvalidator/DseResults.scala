package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import scala.scalajs.js.annotation._



/** Implementation of [[TestResults]] for DsePassage.  Validate
* DsePassage records against a text corpus.
*
* @param corpus Corpus to check DsePassages against.
*/
@JSExportAll case class DseResults[T](corpus: Corpus) extends TestResults[T] {

  /** Implementation of required [[TestResults]] function to evaluate
  * a given DsePassage against the current Corpus.
  */
  def good(dsePassage: T): Boolean = {
    dsePassage match {
      case dse: DsePassage => {
        val matches = corpus ~~ dse.passage
        if (matches.isEmpty) {
          false
        } else {
          true
        }
      }
      case _ => false
    }
  }

  /** Implementation of required [[TestResults]] function.*/
  def report(dsePassage: T):  TestResult = {
    val res = dsePassage match {
      case dse: DsePassage => {
        good(dsePassage) match {
          case true => {
            TestResult(true, "Valid indexing in " + dse)
          }
          case false => {
            TestResult(false, s"Text passage **${dse.passage}** not in corpus (indexed to page ${dse.surface} on image ${dse.imageroi})")
          }
        }
      }
      case _ => TestResult(false, s"Failed: object not a DsePassage:  " + dsePassage)
    }

    //TestResult(good(dsePassage), "Report on " + dsePassage)
    res
  }

}

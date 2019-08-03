package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._

import edu.holycross.shot.cite._

/** Implementation of [[TestResults]] for Strings of characters
* in a given orthographic system.
*
* @param ortho MidOrthography.
*/
@JSExportAll case class CodePointResults[T](ortho: MidOrthography) extends TestResults[T] {

  /**
  */
  def report(str: T):  TestReport = {
    good(str) match {
      case true =>    {
        str match {
          case s: String => {

            TestReport(true, "maybe true? " + s)
          }
        }
      }
      case false => TestReport(false,s"This string is not valid in the current orthographic system (tested with ${ortho}): " + str)
    }
  }

  /**
  */
  def good(str: T): Boolean = {
    str match {
      case s: String =>  {
        ortho.validString(s)
      }

      case _ => {
        println(s"${str}  was NOT a string")
        false
      }
    }
  }
}

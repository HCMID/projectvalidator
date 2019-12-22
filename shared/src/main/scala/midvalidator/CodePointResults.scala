package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._

import edu.holycross.shot.cite._


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


/** Implementation of [[TestResults]] for Strings of characters
* testing whether code points used are valid in a given orthographic system.
*
* @param ortho MidOrthography.
*/
@JSExportAll case class CodePointResults[T](ortho: MidOrthography) extends TestResults[T] with LogSupport {

  /**
  */
  def report(str: T):  TestResult = {
    good(str) match {
      case true =>    {
        str match {
          case s: String => {
            TestResult(true, s"String ${s} uses only valid code points.")
          }
        }
      }
      case false => TestResult(false,s"This string is not valid in the current orthographic system (tested with ${ortho}): " + str)
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
        warn(s"${str}  was NOT a string")
        false
      }
    }
  }



}

package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

/** Implementation of [[TestResults]] testing CitableNodes
* in a given orthographic system for valid tokenization.
*
* @param ortho MidOrthography.
*/
@JSExportAll case class TokenizationResults[T](ortho: MidOrthography) extends TestResults[T] {

  /**
  */
  def report(citableNode: T):  TestReport = {
    good(citableNode) match {
      case true =>    {
        citableNode match {
          case cn: CitableNode => {
            TestReport(true, s"Successfully tokenized node ${cn}")
          }
        }
      }
      case false => TestReport(false,s"This passage could not be tokenized using the specified orthographic system (tested with ${ortho}): " + citableNode)
    }
  }

  /**
  */
  def good(citableNode: T): Boolean = {
    citableNode match {
      case cn: CitableNode =>  {
        val tokens = ortho.tokenizeNode(cn)
        val badTokens = tokens.filter(_.tokenCategory == None)
        badTokens.isEmpty
        //ortho.validString(s)
      }

      case _ => {
        println(s"${citableNode}  was NOT a CitableNode")
        false
      }
    }
  }
}

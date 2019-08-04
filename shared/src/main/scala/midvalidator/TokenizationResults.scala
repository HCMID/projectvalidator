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
  def report(citableNode: T):  TestResult = {
    good(citableNode) match {
      case true =>    {
        citableNode match {
          case cn: CitableNode => {
            TestResult(true, s"Successfully tokenized node ${cn}")
          }
        }
      }
      case false => {
        citableNode match {
          case cn: CitableNode => {
            if (ortho.validString(cn.text)) {
              TestResult(false,s"This passage could not be tokenized using the specified orthography: " + cn.text)
            } else {
              TestResult(false,s"This passage could not be tokenized because there were invalid characters in " + cn.text)
            }

          }
        }
      }

    }
  }

  /**
  */
  def good(citableNode: T): Boolean = {
    citableNode match {
      case cn: CitableNode =>  {
        val tokens = ortho.tokenizeNode(cn)
        val badTokens = tokens.filter(_.tokenCategory == None)
        //println(s"All text OK in ${cn.text}? " + ortho.validString(cn.text))
        //println("Tokens OK? " + badTokens.isEmpty)
        badTokens.isEmpty && ortho.validString(cn.text)

      }

      case _ => {
        println(s"${citableNode}  was NOT a CitableNode")
        false
      }
    }
  }

  def corpusReport = {

  }
}

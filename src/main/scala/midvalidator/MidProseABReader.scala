package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._

import scala.xml._

/** Reads MID prose texts in TEI markup using `ab`
* element as terminal citation unit.
*
* @param editionType Type of edition to read.
*/
class MidProseABReader(applicableType: MidEditionType) extends MidMarkupReader {
  require(editionTypes.contains(applicableType), "Unrecognized edition type: " + applicableType)

  /** Vector of all recognized editionTypes. */
  def editionTypes: Vector[MidEditionType]= {
    Vector(MidDiplomaticEdition)
  }

  /** Specific edition type to apply. */
  def editionType: MidEditionType = applicableType

  /** Create a plain-text citable edition of type editionType
  * in CEX format from source string `archival`.
  *
  * @param archival Archival source text.
  * @param srcUrn URN for archival source text.
  */
  def edition(archival: String, srcUrn: CtsUrn): String = {
    editionType match {
      case MidDiplomaticEdition => MidProseABReader.diplomatic(archival, srcUrn)
    }
  }
}

/**  Implementation of MID model for prose texts encoded
* with terminal citation units in TEI `ab` elements.
*/
object MidProseABReader {
  /** Generate pure diplomatic edition in CEX format.
  *
  * @param xml Archival source in MID-compliant TEI.
  * @param src CtsUrn of archival source edition.
  */
  def diplomatic(xml: String, src: CtsUrn) : String = {
    val root  = XML.loadString(xml)
    collectDiplomatic(root,"")
  }

  def collectDiplomatic(n: xml.Node, s: String): String = {
    //println("Collect diplomatic on " + n.label + " with  s " + s)
    val txt =  StringBuilder.newBuilder
    //txt.append(s)
    n match {
      case t: xml.Text =>  {
        val cleaner = t.toString().trim
        if (cleaner.nonEmpty){
          txt.append(cleaner + " ")
        }
      }

      case e: xml.Elem =>  {
        val appnd = addTextFromElement(e)
        appnd match {
          case None => {
            for (chld <- e.child) {
            //println("\tNow look at " +  chld.label)
             txt.append(collectDiplomatic(chld, txt.toString))
             //txt.append(addTextFromElement(chld))
            }
          }
          case _ => txt.append(appnd.get)
        }


        //println("TEXT IS NOW " + txt.toString)
     }
    }


    //println("Returning " + txt + " from " + n.label)
    txt.toString
  }

  def addTextFromElement(el: xml.Elem): Option[String] = {
    //println("Add text from " + el.label)
    el.label match {
      case "add" => Some(" ")
      case "del" =>  Some(el.text)

      case _ => None
    }
  }

}

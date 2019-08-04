import scala.xml._
import edu.holycross.shot.mid._

/** Awesome regex to select text while keeping
* delimiting string in the resulting match group.
*/
val  includeDelimiterRE = "((?<=%1$s)|(?=%1$s))"

/** RE defining punctuation to use as delimiting String. */
val punctuationRE  = "[\\.,:;?]"


def tokenizeString( baseUrn: String, s:  String, initialIdx: Int = 0) : Vector[String] = {
  val ws = s.split("\\s").filter(_.nonEmpty)
  val depunct = for (w <- ws) yield {
    w.split(String.format(includeDelimiterRE , punctuationRE))
  }
  val tokenVector = depunct.flatten.toVector
  val cex = for (i <- 0 until tokenVector.size) yield {
    s"${baseUrn}.${i}#${tokenVector(i)}"
  }
  cex.toVector
}



trait ChoiceType
case object ABBR_EXPAN extends ChoiceType
case object SIC_CORR extends ChoiceType


// figure out type of `choice` element.
def idChoice(choiceElem: xml.Elem) :  Option[ChoiceType] = {
  val cNames = choiceElem.child.map(_.label).distinct.filterNot(_ == "#PCDATA")
  val abbrExpan = Set("abbr","expan")
  val sicCorr = Set("sic", "corr")

  if (cNames.toSet == abbrExpan ) {
    Some(ABBR_EXPAN)
  } else if (cNames.toSet == sicCorr ) {
    Some(SIC_CORR)
  }  else {
    println("BAD choice : " + cNames)
    None
  }
}

def tokenizeChoice(el: xml.Elem, initialIdx: Int = 0) = {
  val choice = idChoice(el)

  choice match {
    case Some(choiceType) => {
      choiceType match {
        case ABBR_EXPAN => {
          val abbrStr = validator.collectText((el \\ "abbr").toVector.head)
          val expanStr = validator.collectText((el \\ "expan").toVector.head)
          val abbrTokens = tokenizeString("u", abbrStr)
          val expanTokens = tokenizeString("u", expanStr)
          println("abbrTokens: " + abbrTokens.size)
          println("expanTokens: " + expanTokens.size) }
      }
    }

    case None => {
      println("No allowed choice pairing found.")
      throw new Exception("Invalid XML markup in " + el)
    }
  }

}

def tokenizeElement(el: xml.Elem, initialIdx: Int = 0) = {
  el.label match {
    case "choice" => tokenizeChoice(el, initialIdx)

    case lbl: String => "Element " + lbl + " not handled."
  }
}

 val choice1 = XML.loadString("<choice><abbr>mom</abbr><expan>Mandatory ONoing Maintenance</expan></choice>")

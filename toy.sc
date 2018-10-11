import scala.xml._

val  includeDelimiterRE = "((?<=%1$s)|(?=%1$s))"
val punctuationRE  = "[\\.,:;?]"


def tokenizeString( baseUrn: String, s:  String, initialIdx: Int = 0) : Vector[String] = {
  val ws = s.split("\\s").filter(_.nonEmpty)
  val depunct = for (w <- ws) yield {
    w.split(String.format(includeDelimiterRE, punctuationRE))
  }
  val tokenVector = depunct.flatten.toVector
  val cex = for (i <- 0 until tokenVector.size) yield {
    s"${baseUrn}.${i}#${tokenVector(i)}"
  }
  cex.toVector
}

def tokenizeElement(el: xml.Elem, initialIdx: Int = 0) = {
  ""
}

sealed trait ChoiceType
case object ABBR_EXPAN extends ChoiceType
case object SIC_CORR extends ChoiceType


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

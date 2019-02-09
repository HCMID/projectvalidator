package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import org.scalatest.FlatSpec


class JsAccessSpec extends FlatSpec {

  "The MID validator library"  should "expose image managers" in {
    val mgr = ImageManager()
    val defaultICT = "http://www.homermultitext.org/ict2/"
    assert(mgr.ictBase ==  defaultICT)
  }

  it should "expose edition type" in {
    assert (MidDiplomaticEdition.label == "diplomatic")
    assert (MidScribalEdition.label == "scribal")
  }
  it should "expose orthography systems" in  {
    val expectedTypes = Vector(PraenomenToken, PunctuationToken, LexicalToken, NumericToken)
    assert(expectedTypes == Latin23.tokenCategories)
  }
  it should "expose orthography pairings" in  {
    val expectedTypes = Vector(PraenomenToken, PunctuationToken, LexicalToken, NumericToken)
    val pairing = OrthoPairing(CtsUrn("urn:cts:omar:stoa0179.stoa001:"), Latin23)
    assert(pairing.orthography.tokenCategories == expectedTypes)
  }

}

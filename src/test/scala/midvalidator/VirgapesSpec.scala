package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._

class VirgapesSpec extends FlatSpec {


  val neumesDoc = CtsUrn("urn:cts:chant:massordinary.sg359.neumes:")
  val ortho = Virgapes(neumesDoc)

  "A Virgapes orthography" should "have a label" in {
    assert(ortho.orthography == "Virgapes")
  }

  it should "recognize its domain" in {
    assert(ortho.inDomain(CtsUrn("urn:cts:chant:massordinary.sg359.neumes:")))

    assert(ortho.inDomain(CtsUrn("urn:cts:chant:massordinary.sg359.text:")) == false)

  }


  it should "accept digits, white space, periods and hyphens" in {
    assert (ortho.validString("1"))
    assert(ortho.validString("1.0  - 1.1"))
  }


  it should "identify classes of tokens recognizable from this orthography" in {
    val tokenTypes = ortho.tokenCategories
    assert(tokenTypes.toSet == Set(NeumeToken, PunctuationToken))
  }

  it should "tokenize plain-text string for a single neume" in {
    val oneNeume = "1.0.1.0"
    val expectedOne = Vector( Some(MidToken(oneNeume, NeumeToken)))
    assert (ortho.tokenizeString(oneNeume) == expectedOne)
  }

  it should "tokenize plain-text string for a hyphen" in {
    val hyphen = "-"
    val expected = Vector( Some(MidToken(hyphen, PunctuationToken)))
    assert (ortho.tokenizeString(hyphen) == expected)
  }

  it should "tokenize multiple neumes" in {
    val twoNeumes = "1.0.1.0 1.0.0.0"
    val expected = Vector(
      Some(MidToken("1.0.1.0", NeumeToken)),
      Some(MidToken("1.0.0.0", NeumeToken))
    )
    assert (ortho.tokenizeString(twoNeumes) == expected)
  }

  it should "tokenize multiple neumes including multiple neumes on one syllable" in {
    val twoNeumes = "1.0.1.0-2.0.0.0 1.0.0.0"
    val expected = Vector(
      Some(MidToken("1.0.1.0", NeumeToken)),
      Some(MidToken("-", PunctuationToken)),
      Some(MidToken("2.0.0.0", NeumeToken)),
      Some(MidToken("1.0.0.0", NeumeToken))
    )
    assert (ortho.tokenizeString(twoNeumes) == expected)
  }
}

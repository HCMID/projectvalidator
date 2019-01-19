package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import org.scalatest.FlatSpec


class MidOrthographyObjectSpec extends FlatSpec {

  val tokens = Vector(
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.1"), "μῆνιν", Some(LexicalToken)),
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.2"), "ἄειδε", Some(LexicalToken)),
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.3"), ",", Some(PunctuationToken)),
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.4"), "θεά", Some(LexicalToken)),
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.5"), ",", Some(PunctuationToken)),
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.6"), "Πηληϊάδεω", Some(LexicalToken)),
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.7"), "Ἀχιλῆος", Some(LexicalToken)),
    MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:2.1.1"), "οὐλομένην", Some(LexicalToken))
  )

  "The MidOrthography object" should "generate vocabulary lists from lists of tokens" in {
    val actual = MidOrthography.vocabulary(tokens)
    val expected = Vector(",", "Πηληϊάδεω", "θεά", "μῆνιν", "οὐλομένην", "ἄειδε", "Ἀχιλῆος")
    assert(actual == expected)
  }

  it should "generate a concordance of surface forms from lists of tokens" in pending

  it should "generate a histogram of otkens from lists of tokens" in pending

  it should "generate a vector of indexed tokens from a vector of tokens" in pending
}

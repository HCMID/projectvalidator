package edu.holycross.shot.mid.validator
import scala.collection.immutable.ListMap
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

  it should "generate a vector of indexed tokens from a vector of tokens" in {
    val actual = MidOrthography.indexedTokens(tokens)
    val expected = Vector (
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.1"), "μῆνιν", Some(LexicalToken)), 0),
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.2"), "ἄειδε", Some(LexicalToken)), 1),
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.3"), ",", Some(PunctuationToken)), 2),
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.4"), "θεά", Some(LexicalToken)), 3),
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.5"), ",", Some(PunctuationToken)), 4),
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.6"), "Πηληϊάδεω", Some(LexicalToken)), 5),
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.7"), "Ἀχιλῆος", Some(LexicalToken)), 6),
        MidOrthography.IndexedToken(MidToken(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:2.1.1"), "οὐλομένην", Some(LexicalToken)), 7)
      )
    assert(actual == expected)
  }

  it should "generate a concordance of surface forms from lists of tokens" in {

    val expected = ListMap(
      "," -> Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.5")),
      "Πηληϊάδεω" -> Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.6")),
      "θεά" -> Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.4")),
      "μῆνιν" -> Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.1")),
      "οὐλομένην" -> Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:2.1.1")),
      "ἄειδε" -> Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.2")),
      "Ἀχιλῆος" -> Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1.7"))
    )
    val actual = MidOrthography.concordance(tokens)
    assert(actual == expected)
  }

  it should "generate a histogram of tokens from lists of tokens" in {
    val expected = ListMap("," -> 2, "Πηληϊάδεω" -> 1, "θεά" -> 1, "μῆνιν" -> 1, "οὐλομένην" -> 1, "ἄειδε" -> 1, "Ἀχιλῆος" -> 1)
    assert(MidOrthography.tokenHistogram(tokens) == expected)
  }

  


}

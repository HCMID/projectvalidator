package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


class TokenizableCorpusSpec extends FlatSpec {

  val cn1 = CitableNode(
    CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.4.1"),
    "sed debebatur, ut opinor, fatis tantae origo urbis maximi+que secundum deorum opes imperii principium. vi compressa Vestalis cum geminum partum edidisset,")
  val cn2 = CitableNode(CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.4.2"), "seu ita rata, seu quia deus auctor culpae honestior erat, Martem incertae stirpis patrem nuncupat.")
  val nodes = Vector(cn1, cn2)
  val o2corpus = Corpus(nodes)

  "A TokenizableCorpusSpec" should "tokenize a corpus" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val midTokens =  tc.tokenizedCorpus  //Latin23.tokenizeCorpus(o2corpus)
    val expectedTokens = 44
    assert(midTokens.size == expectedTokens)
  }

  it should "create a Vector of MidTokens for a corpus" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val midTokens =  tc.tokens  //Latin23.tokenizeCorpus(o2corpus)
    val expectedTokens = 44
    assert(midTokens.size == expectedTokens)
    midTokens(0) match {
      case tkn: MidToken => assert(true)
      case _ => fail("That was not an MidToken")
    }
  }

  it should "select a Vector of lexical tokens" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val expectedTokens = 37
    assert(tc.lexicalTokens.size == expectedTokens)
  }


  it should "generate an alphabetized list of words" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val distinctWords = 36
    assert(tc.wordList.size == distinctWords)
    val expectedTopFive = Vector("auctor", "compressa", "culpae", "cum", "debebatur")
    assert(tc.wordList.take(5) == expectedTopFive)
  }

  it should "compile a concordance of lexical tokens" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val conc = tc.concordance
    val expectedList = Vector(CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.tkn:1.4.1"))
    assert(conc("opinor") == expectedList)
  }

  it should "compute a histogram of lexical tokens" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val histogram = tc.lexHistogram
    assert(histogram.countForItem("opinor") == 1)
  }

  it should "compute a histogram of token categories" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val histogram = tc.categoryHistogram
    val expectedPunct = 7
    assert(histogram.countForItem(PunctuationToken) == expectedPunct)
  }

  it should "lower case tokens before computing histogram" in {
      val tc = TokenizableCorpus(o2corpus, Latin23)
      val histogram = tc.lexHistogram.frequencies.filter(_.item == "vestalis")
      assert(histogram.size == 1)
  }

  it should "report the size of the corpus in tokens" in {
    val tc = TokenizableCorpus(o2corpus, Latin23)
    val expectedSize = 44
    assert(tc.size == expectedSize)
  }
}

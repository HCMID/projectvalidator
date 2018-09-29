package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


class ValidatorObjectSpec extends FlatSpec {

  "The Validator object" should "profile tokens in a corpus" in pending/* {
    val repo = EditorsRepo("src/test/resources/il10")
    val mom = Validator(repo)
    val profile = Validator.profileTokens(mom.tokens)
    val expectedCategories = Set(LiteralToken, LexicalToken, Unintelligible, Punctuation, NumericToken)
    val actualCategories = profile.map(_._1).toSet

    assert (actualCategories == expectedCategories)
  }
*/
  it should "extract a list of unique lexical words from a list of tokens" in pending /*{
    val repo = EditorsRepo("src/test/resources/il10")
    val mom = Validator(repo)
    val words = Validator.wordList(mom.tokens)
    assert(words.size > 100)
  }*/

  it should "compute a word histogram for a group of tokens" in pending /*{
    val repo = EditorsRepo("src/test/resources/il10")
    val mom = Validator(repo)
    val wordHisto = Validator.wordHisto(mom.tokens)
    val expectedMostFrequent =  "καὶ"
    val actualMostFrequent = wordHisto(0).str
    assert (actualMostFrequent == expectedMostFrequent)
  }*/

  it should "compile a complete index of token occurrences" in pending /*{
    val repo = EditorsRepo("src/test/resources/il10")
    val mom = Validator(repo)
    val wordIdx = Validator.tokenIndex(mom.tokens)
    println("WORD INDEX: " + wordIdx(0))
  }*/

  it should "compute a sequence of StringOccurences from a sequence of tokens" in pending /*{
    val repo = EditorsRepo("src/test/resources/il10")
    val tkns = Validator(repo).tokens
    val stringOccurences = Validator.stringSeq(tkns)
    assert(stringOccurences.size == tkns.size)
    assert(stringOccurences.isInstanceOf[Vector[Occurrence[String]]])
  }*/

  it should "compute a list of output codepoints for a list of tokens" in pending /*{
    val repo = EditorsRepo("src/test/resources/il10")
    val tkns = Validator(repo).tokens
    val cps = Validator.hmtCpsFromTokens(tkns)
    assert(cps.size > 500)
    assert(cps(0).isInstanceOf[Int])
  }
*/
  it should "compute a histogram of characters in a list of tokens" in pending /*{
    val repo = EditorsRepo("src/test/resources/il10")
    val tkns = Validator(repo).tokens
    val tknHisto = Validator.cpHisto(tkns)
    val reverseHisto = tknHisto.reverse
    val lastAndLeast = reverseHisto(0)
    assert(lastAndLeast._2 == 1)
  }*/
  it should "collect all tokens with character-set errors" in pending /*{
    val repo = EditorsRepo("src/test/resources/il10")
    val tkns = Validator(repo).tokens
    val badCharTokens = Validator.badCharTokens(tkns)
    assert((badCharTokens.size > 1) && (badCharTokens.size < tkns.size))
  }*/
  it should "collect all tokens with markup errors" in pending /*&{
    val repo = EditorsRepo("src/test/resources/il10")
    val tkns = Validator(repo).tokens
    val badXmlTokens = Validator.badMarkup(tkns)
    assert(badXmlTokens.size == 1)
  }*/

}

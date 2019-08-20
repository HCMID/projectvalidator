package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import scala.collection.immutable.ListMap
import scala.scalajs.js.annotation._
import edu.holycross.shot.histoutils._
/**
*/
@JSExportAll case class TokenizableCorpus(corpus: Corpus, orthography: MidOrthography) {

  /** Tokenize OHCO2 Corpus in given orthography.*/
  lazy val tokenizedCorpus :  Corpus = {
    orthography.tokenizedCorpus(corpus)
  }

  lazy val tokens :  Vector[MidToken] = {
    corpus.nodes.flatMap(orthography.tokenizeNode(_))
  }

  /** Filter out lexical tokens.*/
  lazy val lexicalTokens  :  Vector[MidToken]  = {
    tokens.filter(_.tokenCategory.toString == "Some(LexicalToken)")
  }

  /** Create alphabetical word list for corpus.*/
  lazy val wordList : Vector[String] = {
    lexicalTokens.map(_.string.toLowerCase).distinct.sorted
  }

  def lexHistogram: Histogram[String] = Histogram(Vector.empty[Frequency[String]])

  def categoryHistogram: Histogram[MidTokenCategory] = Histogram(Vector.empty[Frequency[MidTokenCategory]])

  def concordance: Map[String, CtsUrn] = Map.empty[String,CtsUrn]

}

package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.histoutils._


import scala.scalajs.js.annotation._

/** A class working with an OHCO2 Corpus in a given orthography.
*
* @param corpus Citable corpus.
* @param orthography Orthographic system defining how text can be tokenized.
*/
@JSExportAll case class TokenizableCorpus(corpus: Corpus, orthography: MidOrthography) {


  def size = tokens.size
  
  /** A new Corpus at token level.*/
  lazy val tokenizedCorpus :  Corpus = {
    orthography.tokenizedCorpus(corpus)
  }

  /** Vector of tokens for the corpus.*/
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

  /** Create a histogram of lexical forms.*/
  lazy val lexHistogram: Histogram[String] = {
    val rawCounts = lexicalTokens.map(_.string.toLowerCase).groupBy(w => w).map{ case(k,v) => (k, v.size) }
    val freqs : Vector[Frequency[String ]]= rawCounts.toVector.map{ case (s,i) => Frequency(s,i)}
    val histogram : Histogram[String] = Histogram(freqs.sortWith(_.count > _.count))
    histogram
  }

  lazy val categoryHistogram: Histogram[MidTokenCategory] = {
    val validTokens = tokens.filter(_.tokenCategory != None)

    val rawCounts = validTokens.map(_.tokenCategory.get).groupBy(w => w).map{ case(k,v) => (k, v.size) }
    val freqs : Vector[Frequency[MidTokenCategory ]]= rawCounts.toVector.map{ case (s,i) => Frequency(s,i)}
    val histogram : Histogram[MidTokenCategory] = Histogram(freqs.sortWith(_.count > _.count))
    histogram
  }

  /** Create a traditional concordance of lower-case strings to
  * canonical (containing) passages.
  */
  lazy val concordance : Map[String, Vector[CtsUrn]]  = {
    val formMap = lexicalTokens.groupBy(_.string.toLowerCase)
    formMap.map{case(k,v) => (k, v.map(_.urn.collapsePassageBy(1))) }
  }

}

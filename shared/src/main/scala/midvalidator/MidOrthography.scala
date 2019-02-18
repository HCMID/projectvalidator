package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import scala.collection.immutable.ListMap
import scala.scalajs.js.annotation._

/** An orthographic system
*/
@JSExportAll trait MidOrthography {


  /** Label for this orthographic system.*/
  def orthography: String

  /** True if code point is valid */
  def validCP(cp: Int): Boolean

  /** True if all code points in s are valid.
  *
  * @param s String to test.
  */
  def validString(s: String): Boolean = {
    val tf = strToCps(s).map(validCP(_)).distinct
    ((tf.size == 1) && (tf(0) == true))
  }

  /** Turn a string into a Vector of code points. */
	def strToCps(s: String, cpVector: Vector[Int] = Vector.empty[Int], idx : Int = 0) : Vector[Int] = {
		if (idx >= s.length) {
			cpVector
		} else {
			val cp = s.codePointAt(idx)
			strToCps(s, cpVector :+ cp, idx + java.lang.Character.charCount(cp))
		}
	}

  /** Token categories recognizable from the semantics
  * of this orthography.
  */
  def tokenCategories : Vector[MidTokenCategory]

  /** Tokenize a String in this othography.
  *
  * @param n CitableNode to tokenize.
  */
  def tokenizeNode(n: CitableNode): Vector[MidToken]


  /** Tokenize a `Corpus` into a Vector of [MidToken]s.
  *
  * @param c Corpus to tokenize.
  */
  def tokenizeCorpus(c: Corpus): Vector[MidToken] = {
    val tokens = for (n <- c.nodes) yield {
      tokenizeNode(n)
    }
    tokens.flatten
  }

  /** Create a new corpus citable at the level of the token.
  *
  * @param c Source corpus to analyze.
  */
  def tokenizedCorpus(c: Corpus): Corpus = {
    def tokens = tokenizeCorpus(c)
    Corpus(tokens.map(_.citableNode))
  }

}

/** Singleton object for operating on vectors of [MidToken]s. */
object MidOrthography {


  /** Generate sorted, unique list of  vocabulary items.
  *
  * @param tokens Vector of [[MidToken]]s to extract vocabulary list from.
  */
  def vocabulary(tokens: Vector[MidToken]): Vector[String] ={
    tokens.map(_.string).distinct.sortWith(_ < _)
  }

  /** Generate a concordance of tokens to CtsUrns.
  *
  * @param tokens Vector of [[MidToken]]s to create concordance for.
  */
  def concordance(tokens: Vector[MidToken]): ListMap[String, Vector[CtsUrn]] = {
    val grouped = indexedTokens(tokens).groupBy(_.token.string)
    val mapped = grouped.map{ case (k,v) => (k, v.sortWith(_.index < _.index).map(_.token.urn) )  }
     ListMap(mapped.toSeq.sortBy(_._1):_*)
   }


  /** Generate a histogram of token occurrences
  *
  * @param tokens Vector of [[MidToken]]s to create histogram for.
  */
  def tokenHistogram(tokens: Vector[MidToken]): ListMap[String, Int] = {
    val counts = concordance(tokens).map{ case (k,v) => (k, v.size)}
    ListMap(counts.toSeq.sortWith(_._2 > _._2):_*)
  }

  /** Generate a histogram of occurrences of each token category.
  *
  * @param tokens Vector of [[MidToken]]s to create histogram for.
  */
  def categoryHistogram(tokens: Vector[MidToken]): ListMap[Option[MidTokenCategory], Int] = {
    val grouped = tokens.groupBy(_.tokenCategory).map{ case (k,v) => (k, v.size)}
    ListMap(grouped.toSeq.sortWith(_._2 > _._2):_*)
  }


  /** Generated vector of [[IndexedToken]]s from a vector of [[MidToken]]s.
  *
  * @param tokens Vector of [[MidToken]]s to index.
  */
  def indexedTokens (tokens: Vector[MidToken]): Vector[IndexedToken] = {
    val indexed = for ( (tkn,idx) <- tokens.zipWithIndex) yield {
      IndexedToken(tkn, idx)
    }
    indexed.toVector
  }

  /** Association of an [[MidToken]] with an index of its sequential position
  * in a vector of tokens.
  *
  * @param token MidToken to index.
  * @param index Sequential index of this token in a vector.
  */
  case class IndexedToken(token: MidToken, index: Int)
}

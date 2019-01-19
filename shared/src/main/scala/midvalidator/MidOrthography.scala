package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

/** An orthographic system
*/
trait MidOrthography {


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


  def concordance(tokens: Vector[MidToken]): Map[String, Vector[CtsUrn]] = Map.empty[String, Vector[CtsUrn]]

  def tokenHistogram(tokens: Vector[MidToken]): Map[String, Int] = Map.empty[String, Int]

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

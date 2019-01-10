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

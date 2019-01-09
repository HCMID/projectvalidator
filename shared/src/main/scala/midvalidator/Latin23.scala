package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._


@JSExportTopLevel("Latin23") class Latin23 extends MidOrthography {

  /** Label for this orthographic system.*/
  def orthography: String = "Latin-23"


  // named code points
  /** Tab character.*/
  val tab = 0x9
  /** Newline character.*/
  val nl = 0xA
  /** Carriage return character.*/
  val cr = 0xD
  /** Space character.*/
  val space = 0x20
  /** Collection of all whitespace characters.*/
  val whiteSpace = Vector(space, tab, nl, cr)

  /** Period character.*/
  val period = 0x2e
  /** Hyphen character.*/
  val hyphen = 0x2d
  /** Question mark.*/
  val interrogation = 0x3f
  /** Collection of punctuation characters.*/
  val punctuation:  Vector[Int] = Vector(period, hyphen, interrogation)



  val consonants = "sxnytfmqbglpchrkzd"
  val consonantCPs = for (c <- consonants) yield { c.toInt }
  val vowels = "aeo"
  val vowelCPs =   for (v <- vowels) yield { v.toInt}
  val vcs = "ui"
  val vcCPs = for (ltr <- vcs) yield { ltr.toInt}
  val alphabetic = vowelCPs.toVector ++ vcCPs.toVector

  /** All valid code points. */
  val cpList:  Vector[Int] =  whiteSpace ++ punctuation ++ alphabetic




  /** True if code point is valid */
  def validCP(cp: Int): Boolean ={
    cpList.contains(cp)
  }

  /** Token categories recognizable from the semantics
  * of this orthography.
  */
  def tokenCategories : Vector[MidTokenCategory] = {
    Vector(PraenomenToken, PunctuationToken, LexicalToken, NumericToken)
  }

  /** Tokenize a String in this othography.
  *
  * @param s String to tokenize.
  */
  def tokenizeString(s: String): Vector[MidToken] = {
    val raw = for (t <- s.split("\\s+").filter(_.nonEmpty)) yield {
      MidToken(t, None)
    }
    raw.toVector
  }


  /** Write a description of this orthography in the notatoin of
  * the Stuttgart FST toolkit.
  */
  def toFst : String = {
    "TO BE IMPLEMENTED"
  }
}

package edu.holycross.shot.mid.validator

import edu.holycross.shot.ohco2._
import scala.scalajs.js.annotation._

//@JSExportAll
@JSExportTopLevel("Latin23")
object Latin23 extends MidOrthography {

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

  /** Tokenize a CitableNode in this othography.
  *
  * @param n CitableNode to tokenize.
  * @param exemplarId Value to use for exemplar identifier in exemplar-level URN.
  */
  def tokenizeNode(n: CitableNode, exemplarId: String = "tkn"): Vector[MidToken] = {
    val rawList =  n.text.split("\\s+").filter(_.nonEmpty)
    val raw = for ((t,count) <- rawList.zipWithIndex) yield {
      val psg = n.urn.passageComponent + "." + count
      MidToken(n.urn.addExemplar(exemplarId).addPassage(psg),t, None)
    }
    raw.toVector
  }

  /** Tokenize a CitableNode in this othography.
  *
  * @param n CitableNode to tokenize.
  */
  def tokenizeNode(n: CitableNode):  Vector[MidToken] = {
    tokenizeNode(n, "tkn")
  }

  /** Write a description of this orthography in the notatoin of
  * the Stuttgart FST toolkit.
  */
  def toFst : String = {
    "TO BE IMPLEMENTED"
  }
}

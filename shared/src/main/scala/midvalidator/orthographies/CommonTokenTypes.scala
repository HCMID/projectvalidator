package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._

/** A punctuation token.*/
@JSExportTopLevel("PunctuationToken") case object PunctuationToken extends MidTokenCategory {
  def name = "punctuation"
}

/** A  token.*/
@JSExportTopLevel("LexicalToken") case object LexicalToken extends MidTokenCategory {
  def name = "lexical"
}

/** A numeric token.*/
@JSExportTopLevel("NumericToken") case object NumericToken extends MidTokenCategory {
  def name = "numeric"
}

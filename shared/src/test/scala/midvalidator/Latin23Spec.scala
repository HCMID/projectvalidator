package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec


class Latin23Spec extends FlatSpec {

  "The Latin23 object implementing MidOrthography" should "have token categories" in {
      val expectedTypes = Vector(PraenomenToken, PunctuationToken, LexicalToken, NumericToken)
      assert(expectedTypes == Latin23.tokenCategories)
  }
}

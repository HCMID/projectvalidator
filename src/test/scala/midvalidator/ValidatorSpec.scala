package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import org.homermultitext.edmodel._

class ValidatorSpec extends FlatSpec {

  "A Validator" should "create a CTS corpus of raw XML source" in {
    val repo = EditorsRepo("src/test/resources/il10")
    val mom = Validator(repo)
    assert(mom.raw.isInstanceOf[Corpus])
  }


  it should "create a comprehensive corpus with XML source for diplomatic edition" in {
    val repo = EditorsRepo("src/test/resources/il10")
    val validator = Validator(repo)
    assert(validator.raw.isInstanceOf[Corpus])
  }


}

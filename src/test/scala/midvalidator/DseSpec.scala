package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


class ValidatorDseSpec extends FlatSpec {

  "A validator" should "build a DseVector for the repository" in {
    val repo = EditorsRepo("src/test/resources/iliad10")
    val midValidator = Validator(repo)
    val dseV = midValidator.dse
    assert(dseV.passages.size > 0)
  }

}
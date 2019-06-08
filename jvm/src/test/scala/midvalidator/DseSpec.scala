package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


class ValidatorDseSpec extends FlatSpec {

  "A validator" should "build a DseVector for the repository" in {
    val repo = EditorsRepo("jvm/src/test/resources/chantsample")
    val readers = Vector.empty[ReadersPairing]
    val ortho = Vector.empty[OrthoPairing]
    val midValidator = Validator(repo, readers, ortho)
    val dseV = midValidator.dse
    assert(dseV.passages.size > 0)
  }

  it should "build a DseVector when multiple surfaces are indexed to one image" in {
    val repo = EditorsRepo("jvm/src/test/resources/bifoliosample")
    val readers = Vector.empty[ReadersPairing]
    val ortho = Vector.empty[OrthoPairing]
    val midValidator = Validator(repo, readers, ortho)
    val dseV = midValidator.dse

    println(dseV.passages.size + " passages in bifolio sample.")
  }

}

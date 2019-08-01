package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


class ValidatorDseSpec extends FlatSpec {

  "A validator" should "build a DseVector for the repository when a single text reference spns multiple pages" in pending/* {
    val repo = EditorsRepo("jvm/src/test/resources/chantsample")
    val readers = Vector.empty[ReadersPairing]
    val ortho = Vector.empty[OrthoPairing]
    val midValidator = Validator(repo, readers, ortho)
    val dseV = midValidator.dse
    assert(dseV.passages.size > 0)
  }*/

  it should "build a DseVector when multiple surfaces are indexed to one image" in pending/* {
    val repo = EditorsRepo("jvm/src/test/resources/bifoliosample")
    val readers = Vector.empty[ReadersPairing]
    val ortho = Vector.empty[OrthoPairing]
    val midValidator = Validator(repo, readers, ortho)
    val dseV = midValidator.dse

    val expectedSize = 7
    assert(dseV.passages.size == expectedSize)
  }*/

}

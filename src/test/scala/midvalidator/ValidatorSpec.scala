package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import org.homermultitext.edmodel._

class ValidatorSpec extends FlatSpec {


  val readers = Vector.empty[ReadersPairing]
  val ortho = Vector.empty[OrthoPairing]

  "A Validator" should "create a CTS corpus of raw XML source" in {
    val repo = EditorsRepo("src/test/resources/iliad10")
    val mom = Validator(repo, readers, ortho)
    assert(mom.raw.isInstanceOf[Corpus])
  }




}

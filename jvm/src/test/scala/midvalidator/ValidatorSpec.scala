package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cex._

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._


class ValidatorSpec extends FlatSpec {


  //val repo = EditorsRepo("jvm/src/test/resources/chantsample")
  val readers = Vector (
    ReadersPairing(CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:"),
    MidProseABReader.readers),
    ReadersPairing(CtsUrn("urn:cts:chant:massordinary.sg359.neumes_xml:"),
        MidNeumeReader.readers)
  )
  val ortho = Vector.empty[OrthoPairing]

  "A Validator" should "create a CTS corpus of raw XML source" in pending/* {
    val mom = Validator(repo, readers, ortho)
    assert(mom.raw.isInstanceOf[Corpus])
  }*/

  it should "publish editions from archival source" in pending /*{
    // ensure we start from empty output directory:
    val editionsDir = nameBetterFile(repo.validationDir,"editions")
    if (editionsDir.exists) {
      editionsDir.delete()
    }

    val mom = Validator(repo, readers, ortho)
    mom.publishTexts

    val expected = Vector(
      File("jvm/src/test/resources/chantsample/validation/editions/massordinary.sg359.neumes_xml-dipl.cex"),
      File("jvm/src/test/resources/chantsample/validation/editions/massordinary.sg359.text_xml-dipl.cex")
    )
    val actual = ls(editionsDir).toVector
    assert (actual == expected)
  }*/



}

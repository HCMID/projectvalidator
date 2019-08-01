package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


import better.files._
import File._
import java.io.{File => JFile}

class IlBuilderSpec extends FlatSpec {
  val readers = Vector(ReadersPairing(CtsUrn("urn:cts:greekLit:tlg0012:"), MidVerseLReader.readers), ReadersPairing(CtsUrn("urn:cts:greekLit:tlg5026:"), MidVerseLReader.readers))
  val ortho = Vector.empty[OrthoPairing]
  //val repo = EditorsRepo("jvm/src/test/resources/iliad-build")

  "Twins repo" should "work on Upsilon 1.1 page" in  pending /*{
    val reportsDir = nameBetterFile(repo.validationDir, "e3-109v")
    if (reportsDir.exists) { reportsDir.delete() }

    val mom = Validator(repo, readers, ortho)
    val reporter = ValidationReporter(mom)
    val pg = "urn:cite2:hmt:e3.v1:109v"
    reporter.validate(pg)
    assert(reportsDir.exists)
  }*/

  it should "work on VB page" in pending /*{
    val reportsDir = nameBetterFile(repo.validationDir, "msB-114v")
    if (reportsDir.exists) { reportsDir.delete() }

    val mom = Validator(repo, readers, ortho)
    val reporter = ValidationReporter(mom)
    val pg = "urn:cite2:hmt:msB.v1:114v"
    reporter.validate(pg)
    assert(reportsDir.exists)
  }*/


}

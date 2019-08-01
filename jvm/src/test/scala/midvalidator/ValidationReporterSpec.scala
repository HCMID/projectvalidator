package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


import better.files._
import File._
import java.io.{File => JFile}

class ValidationReporterSpec extends FlatSpec {
  val readers = Vector(ReadersPairing(CtsUrn("urn:cts:greekLit:tlg0012:"), MidVerseLReader.readers), ReadersPairing(CtsUrn("urn:cts:greekLit:tlg5026:"), MidVerseLReader.readers))
  val ortho = Vector.empty[OrthoPairing]

  "A ValidationReporter" should "throw an exception if asked to validate a page with a bad urn" in pending/* {
    val repo = EditorsRepo("jvm/src/test/resources/bifoliosample")
    val midValidator = Validator(repo, readers, ortho)
    val reporter = ValidationReporter(midValidator)

    try {
      reporter.validate("BogusUrn")
      fail("Should not have validated bad urn.")
    } catch {
      case t: Throwable => assert(true)
    }
  }*/

  it should "make a directory for reports based on the collection and object ID" in pending /* {
    val repo = EditorsRepo("jvm/src/test/resources/bifoliosample")
    val mom = Validator(repo, readers, ortho)
    val reporter = ValidationReporter(mom)
    val testUrnStr = "urn:cite2:testns:mom.v1:demopage"
    reporter.validate(testUrnStr)
    val expectedDir = nameBetterFile(repo.validationDir, "mom-demopage")
    assert(expectedDir.exists)
    expectedDir.delete()
  }
 */
  it should "write a DSE report" in pending/* {
    val repo = EditorsRepo("jvm/src/test/resources/iliadsample")
    val reportsDir = nameBetterFile(repo.validationDir, "e3-109v")
    if (reportsDir.exists) { reportsDir.delete() }

    val mom = Validator(repo, readers, ortho)


    val reporter = ValidationReporter(mom)
    val pg = "urn:cite2:hmt:e3.v1:109v"
    reporter.validate(pg)
    assert(reportsDir.exists)

  }*/


}

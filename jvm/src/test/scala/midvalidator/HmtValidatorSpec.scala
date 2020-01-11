package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._
import org.scalatest.FlatSpec


class HmtValidatorSpec extends FlatSpec {

  // Build a CiteLibrary from an EditorsRepo for test data

  val lib =  CiteLibrarySource.fromFile("hmt-withdse.cex")

  "A LibraryValidator" should "validate an HMT release" in {
    val dsev = DseValidator(lib)
    val validatorList = Vector(dsev)
    val pg =   Cite2Urn("urn:cite2:hmt:msA.v1:28r")
    val rslts = LibraryValidator.validate(pg, validatorList)
    println("Library validator: " + rslts.size + " tests run.")
  }


}

package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._

import org.scalatest.FlatSpec


class LibraryValidatorSpec extends FlatSpec {

  // Build a CiteLibrary from an EditorsRepo for test data
  val readerMap = Map.empty[String, Vector[MidMarkupReader]]
  val orthoMap = Map.empty[String, MidOrthography]
  val repo = EditorsRepo("jvm/src/test/resources/chantsample",
  readerMap, orthoMap)
  val lib = repo.library

  "A LibraryValidator" should "apply a list of validators of any type" in pending /*{
    val dsev = DseValidator(lib)
    val validatorList = Vector(dsev)
    val pg =   Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val rslts = LibraryValidator.validate(pg, validatorList)
    println("Library validator: " + rslts.size + " tests run.")
  }*/


}

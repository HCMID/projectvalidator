package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._

import org.scalatest.FlatSpec


class LibraryValidatorSpec extends FlatSpec {

  // Build a CiteLibrary from an EditorsRepo for test data
  val readerMap = Map.empty[String, Vector[MidMarkupReader]]
  val repo = EditorsRepo("jvm/src/test/resources/chantsample",
  readerMap)
  val lib = repo.library

  "A LibraryValidator" should "apply a list of validators of any type" in {
    val dsev = DseValidator(lib)
    val validatorList = Vector(dsev)
    println("DSE Vector for lib: " + DseVector.fromCiteLibrary(lib).size)
    val rslts = LibraryValidator.validate(lib, validatorList)
    println("Library validator: " + rslts.size + " tests run.")
  }


}

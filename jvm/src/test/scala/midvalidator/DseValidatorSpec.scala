package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._

import org.scalatest.FlatSpec


class DseValidatorSpec extends FlatSpec {

  // Build a CiteLibrary from an EditorsRepo for test data
  val readerMap = Map.empty[String, Vector[MidMarkupReader]]
  val orthoMap = Map.empty[String, MidOrthography]
  val repo = EditorsRepo("jvm/src/test/resources/chantsample",
  readerMap, orthoMap)
  val lib = repo.library

  "A DseValidator" should "test DsePassages to see if nodes appear in the library's text corpus" in {
    val dsev = DseValidator(lib)
    val pg =   Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val rslts = dsev.validate(pg)
    println("Tests: " + rslts.size)
    println("Pass/fail: " + dsev.successes(pg).size + "/" + dsev.failures(pg).size)
  }


  it should "identify entries missing from the text corpus" in {
    val dsev = DseValidator(lib)
    val pg =   Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val missing = dsev.failures(pg)
    println(missing.map(_.unit.passage))
  }


}

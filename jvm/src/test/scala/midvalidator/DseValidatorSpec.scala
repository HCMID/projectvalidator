package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import org.scalatest.FlatSpec


class DseValidatorSpec extends FlatSpec {

  // Build a CiteLibrary from an EditorsRepo for test data
  val readerMap = Map.empty[String, Vector[MidMarkupReader]]

  val repo = EditorsRepo("jvm/src/test/resources/chantsample",
  readerMap)
  val lib1 = repo.library
  //val hmtLib = CiteLibrarySource.fromFile("hmt_w_commentary.cex")

  "A DseValidator" should "find a list of text-bearing surfaces in a library" in pending /*{
    val dseValidator = DseValidator(hmtLib)
    val surfaces = dseValidator.tbs
    println("FOUND " + surfaces.size)
  }*/


  it should "validate a range of surfaces" in pending /*{
    val pageList = Vector(
      Cite2Urn("urn:cite2:hmt:msA.v1:77v"),
      Cite2Urn("urn:cite2:hmt:msA.v1:78r")
    )
    val dseValidator = DseValidator(hmtLib)
    val rslts = dseValidator.validate(pageList)
    println("Validated " + pageList.size + " surfaces, and got " + rslts.size + " results.")
  }*/

    /* "do things" in pending

 {
    val dsev = DseValidator(lib)
    val surfaces = dsev.tbs

    val pg =   Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val rslts = dsev.validate(pg)
    println("Tests: " + rslts.size)
    println("Pass/fail: " + dsev.successes(pg).size + "/" + dsev.failures(pg).size)

  } */



  /*
  it should "identify entries missing from the text corpus" in pending

{
    val dsev = DseValidator(hmtLib)
    val pg =   Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val missing = dsev.failures(pg)
    println(missing.map(_.unit.passage))
  }
*/

}

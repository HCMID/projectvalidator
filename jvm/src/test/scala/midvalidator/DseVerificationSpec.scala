package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import org.scalatest.FlatSpec


class DseVerificationSpec extends FlatSpec {

  // Build a CiteLibrary from an EditorsRepo for test data
  val readerMap = Map.empty[String, Vector[MidMarkupReader]]

  val repo = EditorsRepo("jvm/src/test/resources/chantsample",
  readerMap)
  val lib1 = repo.library


  "A DseValidator" should "compose a markdown string for visual verification using the ICT tool" in {
    val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
    val pageList = Vector(pg)
    val dseValidator = DseValidator(lib1)

    println(dseValidator.verify(pg))
  }


}

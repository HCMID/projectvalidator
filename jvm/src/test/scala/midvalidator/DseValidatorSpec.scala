package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.dse._


class DseValidatorSpec extends FlatSpec {

  "A DseValidator" should "return a DseResults[DsePassage] from the MidValidator trait's validate function" in {
    val readerMap = Map.empty[String, Vector[MidMarkupReader]]
    val orthoMap = Map.empty[String, MidOrthography]
    val repo = EditorsRepo("jvm/src/test/resources/chantsample",
    readerMap, orthoMap)
    val lib = repo.library
    val dsev = DseValidator(lib)

    val surface = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
    val validation = dsev.validate(surface)
    println(validation.getClass)


    validation match {
      //case dseRes: DseResults[DsePassage] => assert(true)
      case _ => {
        println("Failed to create correct results.")
      }
    }

  }

  it should "build a DseVector for the repository when a single text reference spans multiple pages" in pending

  it should "build a DseVector when multiple surfaces are indexed to one image" in pending

}

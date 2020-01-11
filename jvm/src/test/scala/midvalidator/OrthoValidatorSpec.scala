package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

import org.scalatest.FlatSpec


class OrthoValidatorSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseABReader" ->   Vector(MidProseABDiplomatic)
  )

  val repoRoot = "jvm/src/test/resources/chantsample"
  val repo = EditorsRepo(repoRoot, readerMap)
  val lib = repo.library

  val textUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:")
  val subCorpus = lib.textRepository.get.corpus ~~ textUrn
  //val rdr = repo.diplomaticReader(textUrn)
  //val dipl = rdr.edition(subCorpus)


  "An OrthographyValidator" should "require a list of MidOrthographys" in {
    val orthoMap : Map[CtsUrn, MidOrthography] = Map(
      CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:") -> Latin23,
      CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:") -> Latin23,
      // THIS IS WRONG:
      CtsUrn("urn:cts:chant:massordinary.sg359.neumes_xml:") -> Latin23
    )
    val orthoVal = OrthographyValidator(orthoMap)
    val rslts = orthoVal.validate(lib)
    println("Validation of orthography: " + rslts.size + " test results.")
  }

}

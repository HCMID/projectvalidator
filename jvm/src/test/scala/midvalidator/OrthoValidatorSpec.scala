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

  val repoRoot = "jvm/src/test/resources/simplelatin"
  val repo = EditorsRepo(repoRoot, readerMap)
  val lib = repo.library

  val textUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:")
  val subCorpus = lib.textRepository.get.corpus ~~ textUrn
  //val rdr = repo.diplomaticReader(textUrn)
  //val dipl = rdr.edition(subCorpus)

  val orthoMap : Map[CtsUrn, MidOrthography] = Map(
    CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:") -> Latin23,
    CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:") -> Latin23,
    // THIS IS WRONG:
    CtsUrn("urn:cts:chant:massordinary.sg359.neumes_xml:") -> Latin23
  )

  "An OrthographyValidator" should "find an orthography for a given URN" in {
    val orthoVal = OrthographyValidator(lib, orthoMap)
    assert(orthoVal.orthoForUrn(textUrn).orthography == "Simple white-space tokenization for testing")
  }

  it should "be easy to make into a TestResultGroup" in {
    val title = "Test corpus from Latin chant project"
    val orthoVal = OrthographyValidator(lib, orthoMap)
    val rslts = orthoVal.validate(lib)
    val resultGroup = TestResultGroup(title, rslts)
    println(resultGroup.summary)
    import java.io.PrintWriter
    new PrintWriter("sample-ortho-output.md"){write(resultGroup.markdown); close;}
  }

}

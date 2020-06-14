
package edu.holycross.shot.mid.validator

import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._


import edu.holycross.shot.citevalidator._
import edu.holycross.shot.mid.markupreader._
import edu.holycross.shot.mid.orthography._


/**
*/
class RepoProcessingSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseABReader" ->   Vector(MidProseABDiplomatic)
  )

  val repoRoot = "jvm/src/test/resources/chantsample"

  val repo = EditorsRepo(repoRoot, readerMap)



  "An EditorsRepo" should "create a diplomatic version of a text" in  pending /*{
      val textUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:")
      val subCorpus = repo.library.textRepository.get.corpus ~~ textUrn

      val rdr = repo.diplomaticReader(textUrn)
      val dipl = rdr.edition(subCorpus)

      assert(subCorpus.size == dipl.size)
  }*/
/*
CiteLibrary(name: String, urn: Cite2Urn, license: String, namespaces: Vector[CiteNamespace], textRepository: Option[TextRepository]

*/
  it should "find a diplomatic MarkupReader for a given CTS URN" in pending /*{
    val testUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:1")
    val expectedDiplReader = "edu.holycross.shot.mid.validator.MidProseABDiplomatic"
    assert(repo.diplomaticReader(testUrn).toString.contains(expectedDiplReader))
  }
*/



}

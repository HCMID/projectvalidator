package edu.holycross.shot.mid.validator

import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

/**
*/
class EditorsRepoSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
  )
  val orthoMap : Map[String, MidOrthography] = Map(
    "Latin23" -> Latin23
  )
  val repoRoot = "jvm/src/test/resources/simplelatin"

  val repo = EditorsRepo(repoRoot, readerMap, orthoMap)



  "An EditorsRepo" should "build a CITE Library from source files" in {
    repo.library match {
      case lib: CiteLibrary => assert(true)
      case _ => fail("Did not create a CiteLibrary")
    }
  }
  it should "construct a DseVector for the records in this repository" in {
    repo.dse match {
      case dses: DseVector => assert(true)
      case _ => fail("Did not create a DseVector")
    }
  }
  it should "constuct a TextRepository for the records in this repository" in {
    repo.texts match {
      case textRepo: TextRepository => assert(true)
      case _ => fail("Did not create a TextRepository")
    }
  }

  it should "build a Vector of OrthoPairings from souce files" in {
    val orthos = repo.orthographies

    val expectedSize = 1
    assert(orthos.size == expectedSize)

    val expectedUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:")
    assert(orthos(0).urn == expectedUrn)

    val expectedOrtho = "edu.holycross.shot.mid.validator.Latin23"
    assert(orthos(0).orthography.toString.contains(expectedOrtho))
  }

  it should "build a Vector of ReaderPairings from source files" in {
    val readers = repo.readers

    val expectedSize = 1
    assert(readers.size == expectedSize)

    val expectedUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:")
    assert(readers(0).urn == expectedUrn)

    val readersVector = readers(0).readers
    val expectedVectorSize = 1
    assert (readersVector.size == expectedVectorSize)

    val expectedReader = "edu.holycross.shot.mid.validator.MidProseABDiplomatic"
    assert(readersVector(0).toString.contains(expectedReader))

  }

  it should "find a diplomatic MarkupReader for a given CTS URN" in {
    val testUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:1")
    val expectedDiplReader = "edu.holycross.shot.mid.validator.MidProseABDiplomatic"
    assert(repo.diplomaticReader(testUrn).toString.contains(expectedDiplReader))
  }


}

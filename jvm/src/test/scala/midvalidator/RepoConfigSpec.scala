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
class RepoConfigSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseAB" ->   Vector(MidProseABDiplomatic)
  )

  val repoRoot = "jvm/src/test/resources/chantsample"

  val repo = EditorsRepo(repoRoot, readerMap)



  "An EditorsRepo" should "build a Vector of OrthoPairings from source files" in  pending /*{
    val orthos = repo.orthographies

    val expectedSize = 1
    assert(orthos.size == expectedSize)

    val expectedUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text:")
    assert(orthos(0).urn == expectedUrn)

    val expectedOrtho = "edu.holycross.shot.mid.validator.Latin23"
    assert(orthos(0).orthography.toString.contains(expectedOrtho))
  }*/

  it should "build a Vector of ReaderPairings from source files" in {
    val readers = repo.readers

    val expectedSize = 2
    assert(readers.size == expectedSize)

    val expectedUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text:")
    assert(readers(0).urn == expectedUrn)

    val readersVector = readers(0).readers
    val expectedVectorSize = 1
    assert (readersVector.size == expectedVectorSize)

    val expectedReader = "edu.holycross.shot.mid.markupreader.MidProseABDiplomatic"
    assert(readersVector(0).toString.contains(expectedReader))

  }

  it should "find a diplomatic MarkupReader for a given CTS URN" in {
    val testUrn = CtsUrn("urn:cts:chant:massordinary.sg359.text:1")
    val expectedDiplReader = "edu.holycross.shot.mid.markupreader.MidProseABDiplomatic"
    assert(repo.diplomaticReader(testUrn).toString.contains(expectedDiplReader))
  }


}
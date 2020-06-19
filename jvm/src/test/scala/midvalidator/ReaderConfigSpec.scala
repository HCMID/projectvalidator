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
class ReaderConfigSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseAB" ->   Vector(MidProseABDiplomatic)
  )
  val repoRoot = "jvm/src/test/resources/chantsample"
  val repo = EditorsRepo(repoRoot, readerMap)

  "An EditorsRepo" should "build a Vector of ReaderPairings from source files" in {
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

  it should "object if configured keys do not agree with keys in the readers map" in {
    val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
      "CONFLICTING_KEY" ->   Vector(MidProseABDiplomatic)
    )
    val repoRoot = "jvm/src/test/resources/chantsample"
    try {
      val repo = EditorsRepo(repoRoot, readerMap)
    } catch {
      case exc: Exception => {
        val expected = "java.lang.Exception: Catastrophe: MidProseAB in textConfig/readers.cex does not match map of MarkupReaders."
        assert(exc.toString == expected)
      }
    }
  }

}

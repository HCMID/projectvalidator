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
class DseVectorSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseAB" ->   Vector(MidProseABDiplomatic)
  )
  val repoRoot = "jvm/src/test/resources/chantsample"
  val repo = EditorsRepo(repoRoot, readerMap)

  "An EditorsRepo" should "construct a DseVector for the records in this repository" in {
    val expectedRecords = 166
    assert(repo.dse.passages.size == expectedRecords)
  }


}

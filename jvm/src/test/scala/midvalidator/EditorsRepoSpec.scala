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
class EditorsRepoSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseAB" ->   Vector(MidProseABDiplomatic)
  )
  val orthoMap : Map[String, MidOrthography] = Map(
    "Latin23" ->   Latin23
  )
  val repoRoot = "jvm/src/test/resources/chantsample"
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
  it should "construct a repository of raw texts for the documents in this repository" in {
    repo.rawTexts match {
      case textRepo: TextRepository => assert(true)
      case _ => fail("Did not create a TextRepository")
    }
  }

}


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
    "MidProseAB" ->   Vector(MidProseABDiplomatic)
  )
  val repoRoot = "jvm/src/test/resources/chantsample"
  val repo = EditorsRepo(repoRoot, readerMap)

  "An EditorsRepo"  should "find a list of subcorpora for the keys in the reader map" in {
    val subCs = repo.subcorpora
    val expectedSize = 2
    assert(subCs.size == expectedSize)

    val firstExpectedWorks =  Vector(CtsUrn("urn:cts:chant:massordinary.sg359.text:"))
    assert(subCs.head.citedWorks == firstExpectedWorks)

    val secondExpectedWorks = Vector(CtsUrn("urn:cts:chant:massordinary.eins121.text:"))
    assert(subCs(1).citedWorks == secondExpectedWorks)
  }

  it should "create a single edition combining all configured editions" in {
    val editions = repo.editions
    val expectedWorks = Set(CtsUrn("urn:cts:chant:massordinary.sg359.text:"),CtsUrn("urn:cts:chant:massordinary.eins121.text:") )
    assert(editions.citedWorks.toSet == expectedWorks)
  }




}

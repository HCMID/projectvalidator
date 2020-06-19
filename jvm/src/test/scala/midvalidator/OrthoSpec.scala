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
class OrthoSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseAB" ->   Vector(MidProseABDiplomatic)
  )
  val orthoMap : Map[String, MidOrthography] = Map(
    "Latin23" ->   Latin23
  )

  val repoRoot = "jvm/src/test/resources/chantsample"
  val repo = EditorsRepo(repoRoot, readerMap, orthoMap)



  "An EditorsRepo" should "recognize orhography mappings" in {
    val orthos = repo.orthographies

    val expectedSize = 2
    assert(orthos.size == expectedSize)

    val expected1 = CtsUrn("urn:cts:chant:massordinary.sg359.text:")
    assert(orthos(0).urn == expected1)

    val expectedOrtho = "edu.holycross.shot.mid.orthography.Latin23"
    assert(orthos(0).orthography.toString.contains(expectedOrtho))

    val expected2 = CtsUrn("urn:cts:chant:massordinary.eins121.text:")
    assert(orthos(1).urn == expected2)
    assert(orthos(1).orthography.toString.contains(expectedOrtho))
  }

  it should "validate values in orthography mappings" in {
    val invalidMap : Map[String, MidOrthography] = Map(
      "INVALID_KEY" ->   Latin23
    )
    try {
      val norepo = EditorsRepo(repoRoot, readerMap, invalidMap)
      fail("Should not have created repo with invalid orthography map.")
    } catch {
      case exc: Exception =>  "Catastrophe: Latin23 in textConfig/orthographies.cex does not match map of MidOrthography classes"
    }

  }

  it should "create subcorpora for orthgraphically mappped texts" in  {
    val subCs = repo.tokencorpora
    val expectedSize = 2
    assert(subCs.size == expectedSize)

    val firstExpectedWorks =  Vector(CtsUrn("urn:cts:chant:massordinary.sg359.text:"))
    assert(subCs.head.citedWorks == firstExpectedWorks)

    val secondExpectedWorks = Vector(CtsUrn("urn:cts:chant:massordinary.eins121.text:"))
    assert(subCs(1).citedWorks == secondExpectedWorks)
  }

  it should "create editions for each subcorpus tokenized at exemplar level" in {
    val newEdd = repo.tokenized
    val expected = Vector(
      CtsUrn("urn:cts:chant:massordinary.sg359.text.lat23tkn:"), CtsUrn("urn:cts:chant:massordinary.eins121.text.lat23tkn:")
    )
    assert(newEdd.citedWorks == expected)
  }

  it should "compose a catalog of the tokenized exemplar editions" in {
    val catalog = repo.tokenizedCatalog

    val expected = Vector(
      CtsUrn("urn:cts:chant:massordinary.sg359.text.lat23tkn:"), CtsUrn("urn:cts:chant:massordinary.eins121.text.lat23tkn:")
    )
    assert(catalog.texts.map(_.urn) == expected)

  }

}

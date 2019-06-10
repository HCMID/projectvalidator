package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.dse._


class DseReporterSpec extends FlatSpec {

  val dseFile = "jvm/src/test/resources/bifoliosample/dse/e3_dse.cex"
  val dummyCollection = Cite2Urn("urn:cite2:units:dummy.v1:")
  val dsev = DseSource.fromTriplesFile(dseFile,dummyCollection)

  val txtDir = "jvm/src/test/resources/bifoliosample/editions"
  val txtCatalog = s"${txtDir}/catalog.cex"
  val txtConfig = s"${txtDir}/citation.cex"

  val corpus = TextRepositorySource.fromFiles(txtCatalog, txtConfig, txtDir).corpus

  "A DseReporter" should "do things" in {
    val pg = Cite2Urn("urn:cite2:hmt:e3.v1:109v")
    val reporter = DseReporter(pg, dsev, corpus)

    val corpusUrns = (corpus.nodes.map(_.urn))
    assert(reporter.missingPassages.isEmpty)
  }

  it should "handle a range of pages" in {
      val pgs = Cite2Urn("urn:cite2:hmt:e3.v1:109v-110r")
      val reporter = DseReporter(pgs, dsev, corpus)
      assert(reporter.missingPassages.isEmpty)
  }
}

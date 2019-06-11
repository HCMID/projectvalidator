package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.dse._



class DseReporterSpec extends FlatSpec {

  val dseFile = "jvm/src/test/resources/bifoliosample/dse/e3_dse.cex"
  val scholiaFile = "jvm/src/test/resources/bifoliosample/dse/scholia_e3.cex"
  val dummyCollection = Cite2Urn("urn:cite2:units:dummy.v1:")
  val scholdse = DseSource.fromTriplesFile(scholiaFile,dummyCollection)
  val iliaddse = DseSource.fromTriplesFile(dseFile,dummyCollection)

  val txtDir = "jvm/src/test/resources/bifoliosample/editions"
  val txtCatalog = s"${txtDir}/catalog.cex"
  val txtConfig = s"${txtDir}/citation.cex"

  val readersDummy = Vector.empty[ReadersPairing]

  val corpus = TextRepositorySource.fromFiles(txtCatalog, txtConfig, txtDir).corpus

  "A DseReporter" should "do things" in {
    val pg = Cite2Urn("urn:cite2:hmt:e3.v1:109v")
    val reporter = DseReporter(pg, iliaddse, corpus,readersDummy )

    val corpusUrns = (corpus.nodes.map(_.urn))
    assert(reporter.missingPassages.isEmpty)
  }

  it should "handle a range of pages" in {
      val pgs = Cite2Urn("urn:cite2:hmt:e3.v1:109v-110r")
      val reporter = DseReporter(pgs, iliaddse, corpus,readersDummy )
      assert(reporter.missingPassages.isEmpty)
  }

  it should "create markdown for a passage view of the report" in {

      val txtDir = "jvm/src/test/resources/iliadsample/editions"
      val txtCatalog = s"${txtDir}/catalog.cex"
      val txtConfig = s"${txtDir}/citation.cex"

      val iliadCorpus = TextRepositorySource.fromFiles(txtCatalog, txtConfig, txtDir).corpus


      val dseCex = "jvm/src/test/resources/iliadsample/dse/e3_dse.cex"
      val dse = DseSource.fromTriplesFile(dseCex,dummyCollection)


      val pg = Cite2Urn("urn:cite2:hmt:e3.v1:109v")
      //println("IL IDS:")
      //println(dse.passages.map(_.passage).mkString("\n"))


      val readers = Vector(ReadersPairing(CtsUrn("urn:cts:greekLit:tlg0012:"), MidVerseLReader.readers))

      val reporter = DseReporter(pg, dse, iliadCorpus,readers )
      //println("Indexed in DSE:")
      //println(dse.passages.map(_.passage).mkString("\n"))
      val lines = reporter.passageView.split("\n").filter(_.nonEmpty)
      val expectedEntries = 4
      assert(lines.size == expectedEntries)

  }
}

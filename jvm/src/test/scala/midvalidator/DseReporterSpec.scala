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

  it should "format a markdown string for a passage view of a given CtsUrn" in {
      val txtDir = "jvm/src/test/resources/iliadsample/editions"
      val txtCatalog = s"${txtDir}/catalog.cex"
      val txtConfig = s"${txtDir}/citation.cex"
      val iliadCorpus = TextRepositorySource.fromFiles(txtCatalog, txtConfig, txtDir).corpus
      val dseCex = "jvm/src/test/resources/iliadsample/dse/e3_dse.cex"
      val dse = DseSource.fromTriplesFile(dseCex,dummyCollection)
      val pg = Cite2Urn("urn:cite2:hmt:e3.v1:109v")
      val readers = Vector(ReadersPairing(CtsUrn("urn:cts:greekLit:tlg0012:"), MidVerseLReader.readers))
      val reporter = DseReporter(pg, dse, iliadCorpus,readers)
      val u = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.e3:9.2")
      val md = reporter.passageMarkdown(u)
      val expected = """θεσπεσίη ἔχε φύζα φόβου κρυόεντος ἑταίρη-  (*urn:cts:greekLit:tlg0012.tlg001.e3:9.2*)  [![image](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/e3bifolio/v1/E3_109v_110r.tif&RGN=0.2427,0.3426,0.2094,0.02245&WID=1000&CVT=JPEG)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:e3bifolio.v1:E3_109v_110r@0.2427,0.3426,0.2094,0.02245)"""
      assert (md == expected)

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

  it should "report on completeness of coverage in DSE" in {
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
    println("COMPLETENESS:  \n" + reporter.dseCompleteness)
  }
}

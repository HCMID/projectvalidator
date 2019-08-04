package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._

import org.scalatest.FlatSpec


class DseResultsSpec extends FlatSpec {


 val dseStr = "urn:cts:chant:massordinary.sg359.text_xml:h007_2.h10.1#urn:cite2:ecod:codsang359imgs.v1:csg359_0_43_36_0@0.6863,0.2851,0.1977,0.02985#urn:cite2:ecod:sg359pages.v1:36"



 ///urn: Cite2Urn, label: String, passage: CtsUrn, imageroi: Cite2Urn, surface: Cite2Urn)
  val dsePsg = DsePassage(
    Cite2Urn("urn:cite2:hcmid:units.v1:dsesg1"),
    "Demo text",
    CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:h007_2.h10.1"), Cite2Urn("urn:cite2:ecod:codsang359imgs.v1:csg359_0_43_36_0@0.6863,0.2851,0.1977,0.02985"),
    Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
  )
  val urn = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:h007_2.h10.1")
  val txt = "Caeli enarrant."
  val cn = CitableNode(urn,txt)
  val corpus = Corpus(Vector(cn))
  val dseResults :  DseResults[DsePassage] = DseResults(corpus)

  "A DseResults" should "determine if a DsePassage reports a node in the given corpus" in {
    val rept = dseResults.report(dsePsg)
    //println("IN DSE: " + dsePsg.passage)
    //println("IN CORPUS: " + corpus.nodes.map(_.urn).mkString(", "))
    assert(rept.success)
  }

  it should "identify entries missing from the text corpus" in {
    val absent = CtsUrn("urn:cts:chant:massordinary.sg359.text_xml:NOT_HERE")
    val dsePsg = DsePassage(
      Cite2Urn("urn:cite2:hcmid:units.v1:dsesg1"),
      "Demo text",
      absent, Cite2Urn("urn:cite2:ecod:codsang359imgs.v1:csg359_0_43_36_0@0.6863,0.2851,0.1977,0.02985"),
      Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    )
    val rept = dseResults.report(dsePsg)
    assert(rept.success == false)
  }
  it should "return a TestReport" in {
    val rept = dseResults.report(dsePsg)
    rept match {
      case tr: TestReport => assert(true)
      case _ => fail("Not a TestReport: " + rept)
    }
  }


}

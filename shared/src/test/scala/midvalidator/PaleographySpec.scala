package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._



class PaleographySpec extends FlatSpec {


  // Ten valid observations:
  val tenGoodCex = """urn:cite2:op:208v:1#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ο#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.4959,0.2445,0.01474,0.01355#
urn:cite2:op:208v:2#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ρ#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5107,0.2445,0.007922,0.01591#
urn:cite2:op:208v:3#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@σ#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5232,0.2425,0.01271,0.01632#
urn:cite2:op:208v:4#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ε#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5345,0.2436,0.01124,0.01286#
urn:cite2:op:208v:5#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ο[2]#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5437,0.2451,0.01124,0.01051#
urn:cite2:op:208v:6#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@δ#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5564,0.2387,0.01437,0.01577#
urn:cite2:op:208v:7#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ι#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5674,0.2447,0.01142,0.01134#
urn:cite2:op:208v:8#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ο[3]#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5787,0.2425,0.008106,0.01494#
urn:cite2:op:208v:9#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@γ#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5892,0.2433,0.01013,0.01494#
urn:cite2:op:208v:10#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ε[2]#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.5980,0.2407,0.01013,0.01494#
"""
  "A PaleographyResults" should "use the report function to report on a single line of CEX source" in {
    val palResults :  PaleographyResults[String] = new PaleographyResults
    val oneLine = "urn:cite2:op:208v:1#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ο#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.4959,0.2445,0.01474,0.01355#"
    val rept = palResults.report(oneLine)
    assert(rept.success)

  }

  it should "implement the TestResults trait's good function" in {
    val palResults :  PaleographyResults[String] = new PaleographyResults
    val oneLine = "urn:cite2:op:208v:1#urn:cts:greekLit:tlg0012.tlg001.msA.hmt2017a:16.126@ο#urn:cite2:hmt:vaimg.2017a:VA208VN_0710@0.4959,0.2445,0.01474,0.01355#"
    assert(palResults.good(oneLine))
  }

  it should "identify bad entries" in {
    val badCex = "Not a URN#Bad value"
    val palResults :  PaleographyResults[String] = new PaleographyResults

    assert(palResults.good(badCex) == false)
    val expectedMsg = "Unable to form valid paleographic observation from String Not a URN#Bad value"
    assert(palResults.report(badCex).summary == expectedMsg)
  }

  it should "report on a Vector of Strings" in pending /* {
    val lines = tenGoodCex.split("\n").toVector
    val palResults :  PaleographyResults[String] = new PaleographyResults
    val rept = palResults.reports(lines)

    val expectedTotal = 10
    assert(rept.size == expectedTotal)

    val expectedSuccess = 10
    assert(rept.filter(_.success).size == expectedSuccess)

    val expectedFails = 0
    assert(rept.filterNot(_.success).size == expectedFails)

  }*/
}

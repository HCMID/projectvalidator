package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._



class CodePointsSpec extends FlatSpec {


  val cpResults :  CodePointResults[String] = new CodePointResults(Latin23)

  "A CodePointResults" should "use the report function to report on a string" in {
    val rept = cpResults.report("casus belli")
    assert(rept.success)
  }

  it should "implement the TestResults trait's good function" in {
    assert(cpResults.good("casus belli"))
  }

  it should "identify bad entries" in  {
    val badString  = "μῆνις"

    assert(cpResults.good(badString) == false)

    val expectedMsg = "This string is not valid"
    assert(cpResults.report(badString).summary.contains(expectedMsg))
  }

  it should "report on a Vector of Strings" in pending /*{
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

package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._



class TokenizationSpec extends FlatSpec {

  val urn = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.1.1")
  val txt = "iam primum omnium satis constat Troia capta in ceteros saeuitum esse Troianos, duobus, Aeneae Antenorique, et uetusti iure hospitii et quia pacis reddendaeque Helenae semper auctores fuerant, omne ius belli Achiuos abstinuisse;".toLowerCase
  val cn = CitableNode(urn,txt)
  val corpus = Corpus(Vector(cn))
  val tknResults :  TokenizationResults[CitableNode] = TokenizationResults(Latin23)


  "A TokenzationResults" should "use the report function to report on a single CitableNode" in{
    val rept = tknResults.report(cn)
    assert(rept.success)
  }

  it should "implement the TestResults trait's good function" in {
    assert(tknResults.good(cn))
  }

  it should "identify bad entries" in  {
    val badText = "μῆνιν ἄειδε"
    val badNode = CitableNode(urn, badText)

    assert(tknResults.good(badNode) == false)

    val expectedMsg = "This passage could not be tokenized because there were invalid characters in μῆνιν ἄειδε"
    assert(tknResults.report(badNode).summary == expectedMsg)

  }

  it should "support reporting on a corpus using the reports function" in {
    val repts = tknResults.reports(corpus.nodes)
    val expectedSize = 1
    assert(repts.size == expectedSize)
  }
}

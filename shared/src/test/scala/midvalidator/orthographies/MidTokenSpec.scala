package edu.holycross.shot.mid.validator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import org.scalatest.FlatSpec


class MidTokenSpec extends FlatSpec {

  "An MidToken" should "generate a CitableNode" in {

      val urn = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.1.1")
      val txt = "iam primum omnium satis constat Troia capta in ceteros saevitum esse Troianos, duobus, Aeneae Antenorique, et vetusti iure hospitii et quia pacis reddendaeque Helenae semper auctores fuerant, omne ius belli Achiuos abstinuisse;"
      val expectedNode = CitableNode(urn, txt)
      val tkn = MidToken(urn,txt,Some(LexicalToken))

      assert(tkn.citableNode == expectedNode)
  }
}

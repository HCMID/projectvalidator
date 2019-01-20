package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

class MidProseABReaderSpec extends FlatSpec {

  "An MidProseABReader" should "create diplomatic CEX from a parsed node" in {
    val xml = "<div n=\"1\"><ab>Text 1<del>.1</del><add>.2</add> version</ab></div>"
    val urn =CtsUrn("urn:cts:mid:unittests.1.xml:1")
    val reader = MidProseABReader(MidDiplomaticEdition)
    val actual = reader.editedNodeCex(urn, xml)
    val expected = "urn:cts:mid:unittests.1.xml_dipl:1#Text 1 .1 version"
    assert (actual.trim == expected)
  }

}

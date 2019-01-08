package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

class MidProseABReaderSpec extends FlatSpec {

  "The MidProseABReader object" should "recursively collect text from XML containers"  in  {
    val xml = "<div n=\"1\"><ab>Text 1</ab></div>"
    // expect trailing space so xml elements are
    // not concatenated

    val expected = "Text 1 "
    val actual = MidProseABReader.diplomatic(xml)
    assert(actual == expected)
  }

  it should "thrown an exception if elements outside MID standard are found" in {
    val xml = "<div><watermark>Agamemnon</watermark></div>"
    try {
      MidProseABReader.diplomatic(xml)
    } catch {
      case e: Exception => assert(e.getMessage == "Unrecognized XML element: watermark")
    }
  }

  it should "ignore added content in diplomatic editions" in {
    val xml = "<div n=\"1\"><ab>Text 1<add>.1</add> version</ab></div>"
    // expect trailing space so xml elements are
    // not concatenated

    val expected = "Text 1 version "
    val actual = MidProseABReader.diplomatic(xml)
    assert(actual.replaceAll("[ ]+", " ") == expected)
  }


  it should "ignore include content deleted later in diplomatic editions" in {
    val xml = "<div n=\"1\"><ab>Text 1<del>.1</del><add>.2</add> version</ab></div>"
    // expect trailing space so xml elements are
    // not concatenated

    val expected = "Text 1 .1 version "
    val actual = MidProseABReader.diplomatic(xml)
    assert(actual == expected)
  }

  "An MidProseABReader" should "create diplomatic CEX from a parsed node" in {
    val xml = "<div n=\"1\"><ab>Text 1<del>.1</del><add>.2</add> version</ab></div>"
    val urn =CtsUrn("urn:cts:mid:unittests.1.xml:1")
    val reader = MidProseABReader(MidDiplomaticEdition)
    val actual = reader.editedNode(xml, urn)
    val expected = "urn:cts:mid:unittests.1.xml_dipl:1#Text 1 .1 version"
    assert (actual.trim == expected)
  }

}

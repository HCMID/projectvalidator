package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec


import scala.xml._
class XmlTextCollectSpec extends FlatSpec {

  "The package object" should "recursively collect text contents of an XML node" in {
    val xml = XML.loadString("<root>Level 1 <div>contained 2</div><div>, more two <sub>third tier</sub> and back to two</div></root>")
    val actual = collectText(xml).trim
    val expected = "Level 1 contained 2 , more two third tier and back to two"
    assert(actual == expected)

  }
}

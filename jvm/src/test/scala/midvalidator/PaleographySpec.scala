package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import scala.io.Source


class PaleographySpec extends FlatSpec {

  "The PaleographyResults object" should "analyze CEX as TestResults" in {
    val f = "jvm/src/test/resources/paleography-greek-example.cex"
    val cex = Source.fromFile(f).getLines.toVector.tail.mkString("\n")
    val testOutput = PaleographyResults(cex)
    assert(testOutput.good.size == 10)
    assert(testOutput.bad.size == 0)
  }
}

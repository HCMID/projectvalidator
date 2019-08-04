package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


import better.files._
import File._
import java.io.{File => JFile}

class ValidationReporterSpec extends FlatSpec {

  val repoRoot = "jvm/src/test/resources/simplelatin"
  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
  )
  val orthoMap : Map[String, MidOrthography] = Map(
    "Latin23" -> Latin23
  )
  val repo = EditorsRepo(repoRoot, readerMap, orthoMap)



  "A ValidationReporter" should "throw an exception if asked to validate a page with a bad urn" in pending

  it should "make a directory for reports based on the collection and object ID" in pending

  it should "write a DSE report" in pending

  it should "do all the magic" in {
    val reporter = ValidationReporter(repo)
    val urn = Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    reporter.validate(urn)
  }


}

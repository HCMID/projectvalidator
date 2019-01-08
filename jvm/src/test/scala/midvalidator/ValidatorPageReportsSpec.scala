package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


import better.files._
import File._
import java.io.{File => JFile}

class ValidationPageReportsSpec extends FlatSpec {
  val readers = Vector.empty[ReadersPairing]
  val ortho = Vector.empty[OrthoPairing]

  "A ValidationReporter" should "write a DSE report" in {
    val repo = EditorsRepo("jvm/src/test/resources/chantsample")
    val mom = Validator(repo, readers, ortho)
    val reporter = ValidationReporter(mom)
    val pg = "urn:cite2:ecod:eins121pages.v1:21"
    reporter.validate(pg)
  }
/*
  it should "parse natarch" in {
      val repo = EditorsRepo("natarch")
      val mom = Validator(repo, readers, ortho)
      val reporter = ValidationReporter(mom)
      val pg = "urn:cite2:mid:declparchment.v1:front"
      reporter.validate(pg)
  }

  it should "write another DSE report" in {
    val repo = EditorsRepo("jvm/src/test/resources/iliad10")
    val mom = Validator(repo)
    val reporter = ValidationReporter(mom)
    val pg = "urn:cite2:hmt:msB.v1:128v"
    reporter.validate(pg)
  }
*/
}
package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cex._

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._


class ReaderOrthoSpec extends FlatSpec {

  val repoPath = "jvm/src/test/resources/bifoliosample"

  "The Validator object" should "be able to configure readers by convention" in pending /*(){
    Validator(repoPath)

  }*/

  it should "be able to configure orthographies by convention" in pending
}

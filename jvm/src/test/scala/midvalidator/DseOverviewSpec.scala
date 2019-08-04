package edu.holycross.shot.mid.validator

import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

/**
*/
class DseOverviewSpec extends FlatSpec {

  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
  )
  val orthoMap : Map[String, MidOrthography] = Map(
    "Latin23" -> Latin23
  )
  val repoRoot = "jvm/src/test/resources/simplelatin"

  val repo = EditorsRepo(repoRoot, readerMap, orthoMap)



  "A DseOverview" should "do the overvew things" in pending
  

}

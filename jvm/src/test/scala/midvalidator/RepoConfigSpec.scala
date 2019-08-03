package edu.holycross.shot.mid.validator

import org.scalatest.FlatSpec

import edu.holycross.shot.cite._


/**
*/
class RepoConfigSpec extends FlatSpec {


  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
  )
  val orthoMap : Map[String, MidOrthography] = Map(
    "Latin23" -> Latin23
  )

  "An EditorsRepo" should "fail if missing dse dir" in {
    val badSrc = "jvm/src/test/resources/nodse"
    try {
      val repo = EditorsRepo(badSrc, readerMap, orthoMap)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }
  it  should "fail if missing editing dir" in {
    val badSrc = "jvm/src/test/resources/noeditions"
    try {
      val repo = EditorsRepo(badSrc, readerMap, orthoMap)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }
  it  should "fail if missing validation dir" in {
    val badSrc = "jvm/src/test/resources/novalidation"
    try {
      val repo = EditorsRepo(badSrc, readerMap, orthoMap)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }
  it  should "fail if missing paleography dir" in  {
    val badSrc = "jvm/src/test/resources/nopaleography"
    try {
      val repo = EditorsRepo(badSrc, readerMap, orthoMap)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }

  it  should "fail if missing CTS catalog" in {
    val badSrc = "jvm/src/test/resources/noctscatalog"
    try {
      val repo = EditorsRepo(badSrc, readerMap, orthoMap)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }

  it  should "fail if missing CTS citation configuration" in {
    val badSrc = "jvm/src/test/resources/noctscitation"
    try {
      val repo = EditorsRepo(badSrc, readerMap, orthoMap)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }


}

package edu.holycross.shot.mid.validator

import org.scalatest.FlatSpec

import edu.holycross.shot.cite._


/**
*/
class RepoConfigSpec extends FlatSpec {

  "An EditorsRepo" should "fail if missing dse dir" in pending /*{
    val badSrc = "jvm/src/test/resources/nodse"
    try {
      val repo = EditorsRepo(badSrc)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }*/
  it  should "fail if missing editing dir" in pending /*{
    val badSrc = "jvm/src/test/resources/noeditions"
    try {
      val repo = EditorsRepo(badSrc)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }*/
  it  should "fail if missing validation dir" in pending /*{
    val badSrc = "jvm/src/test/resources/novalidation"
    try {
      val repo = EditorsRepo(badSrc)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }*/
  it  should "fail if missing paleography dir" in pending /* {
    val badSrc = "jvm/src/test/resources/nopaleography"
    try {
      val repo = EditorsRepo(badSrc)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }
*/
  it  should "fail if missing CTS catalog" in pending /*{
    val badSrc = "jvm/src/test/resources/noctscatalog"
    try {
      val repo = EditorsRepo(badSrc)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }*/

  it  should "fail if missing CTS citation configuration" in pending /*{
    val badSrc = "jvm/src/test/resources/noctscitation"
    try {
      val repo = EditorsRepo(badSrc)
      fail("Should not have created repo.")
    } catch {
      case err: Throwable => assert(true)
    }
  }
*/

}

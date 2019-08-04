package edu.holycross.shot.mid.validator

import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

/* Easier to test DseOverview from local files source even
* though it operates off a CiteLibrary.
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
  val lib = repo.library

  val badRepoRoot = "jvm/src/test/resources/simplelatin-bad"
  val badRepo = EditorsRepo(badRepoRoot, readerMap, orthoMap)
  val badLib = badRepo.library


  "A DseOverview" should "get basic summary scores by corpus and by surface" in {
    val dseOv = DseOverview(lib, repo.readers)

    val expectedCorpusSuccess = 84
    val expectedCorpusFailure = 0

    assert(dseOv.successesAll == expectedCorpusSuccess)
    assert(dseOv.failuresAll == expectedCorpusFailure)

    val pg = Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val pgDse = dseOv.dse.passages.filter(_.surface == pg)

    val expectedReports = 17
    val expectedPageSuccess = 17
    val expectedPageFailure = 0

    val pgRepts = dseOv.testResults.reports(pgDse)
    assert(pgRepts.size == expectedReports)
    assert(dseOv.successes(pg) == expectedPageSuccess)
    assert(dseOv.failures(pg) == expectedPageFailure)
  }

  it should "cope with errors" in {

    val pg = Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val dseOv = DseOverview(badLib, badRepo.readers)
    println("CORPUS SUCCESS/FAIL:\n" + dseOv.successesAll + "/" + dseOv.failuresAll)

    //val pgRepts = dseOv.testResults.reports(pgDse)


    println("PAGE SUCCESS/FAIL:\n" + dseOv.successes(pg) + "/" + dseOv.failures(pg))


  }

  it should "cope with image service configs" in {
    //BinaryImageService

    val baseUrl  = "http://www.homermultitext.org/iipsrv?"
    val imagePath = "/project/homer/pyramidal/deepzoom/ecod/codsang359imgs/v1/"
    val img = Cite2Urn("urn:cite2:ecod:codsang359imgs.v1:csg359_0_43_36_0@0.3888,0.2488,0.04748,0.03466")
  }


}

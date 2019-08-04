package edu.holycross.shot.mid.validator

import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._

// Exercise this class using a DseOverview object
class ReportWriterSpec extends FlatSpec {

  // Lengthy set up, but simplifies this suite of tests.
  // Tests will use the EditorsRepo as well as reports
  // generated by DseOverview from the CiteLibrary generated
  // by the repo.
  val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
    "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
  )
  val orthoMap : Map[String, MidOrthography] = Map(
    "Latin23" -> Latin23
  )
  val repoRoot = "jvm/src/test/resources/simplelatin"
  val repo = EditorsRepo(repoRoot, readerMap, orthoMap)
  val lib = repo.library
  val dseOv = DseOverview(lib)
  val reptWriter = ReportWriter(repo.validationDir)


  def tidy(fileList :Seq[File]) = {
    for (f <- fileList) {
      if (f.exists) {rm(f)}
    }
  }

  // Tests:
  "A ReportWriter" should "write 'em'" in {
    val pg = Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val reports = dseOv.reportPages(pg)
    val fileList = reports.map(rept => repo.validationDir/rept.suggestedFileName)
    // rm files if already there:
    tidy(fileList)


    val reptWriter = ReportWriter(repo.validationDir)
    reptWriter.writeReports(reports)/*
    // test that resulting report files exist
    for (f <- fileList) {
      assert(f.exists)
    }
    // rm files before further testing
    tidy(fileList) */
  }
  it should "object if a writable directory is not found" in pending /*{
    val bogus = File("Not_a_directory")
    try {
      val reptWriter = ReportWriter(bogus)
      fail("Should not have created writer.")
    } catch {
      case iae: IllegalArgumentException => {
        val expected = "Valdiation directory not found"
        assert(iae.toString.contains(expected))
      }
      case t: Throwable => println("QUIT ON " + t)
    }
  }&*/
}
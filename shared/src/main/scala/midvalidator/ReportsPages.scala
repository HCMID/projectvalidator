package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import scala.scalajs.js.annotation._


/** Overview of results for tests on a group of artifacts.*/
trait ReportOverview {
  def successes(surface: Cite2Urn): Int
  def failures(surface: Cite2Urn): Int
  def reportPages(surface: Cite2Urn): Vector[ReportPage]
}

/** Human-readable content summarizing a report.*/
@JSExportAll case class ReportPage(
  title: String,
  markdown: String,
  suggestedFileName: String
)

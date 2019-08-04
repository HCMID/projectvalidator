package edu.holycross.shot.mid.validator

import scala.scalajs.js.annotation._


/** Overview of results for tests on a group of artifacts.*/
trait ReportOverview {
  def successes: Int
  def failures: Int
  def reportPages: Vector[ReportPage]
}

/** Human-readable content summarizing a report.*/
@JSExportAll case class ReportPage(
  title: String,
  markdown: String,
  suggestedFileName: String
)

package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import scala.scalajs.js.annotation._


/** Overview of results for tests on a group of artifacts
* occuring on a specified text-bearing surface.
*/
trait ReportOverview {

  /** Number of successful reports.
  *
  * @param surface Text-bearing surface.
  */
  def successes(surface: Cite2Urn): Int

  /** Number of unsuccessful reports.
  *
  * @param surface Text-bearing surface.
  */
  def failures(surface: Cite2Urn): Int

  /** Report pages for each artifact on surface.
  *
  * @param surface Text-bearing surface.
  */
  def reportPages(surface: Cite2Urn): Vector[ReportPage]
}

/** Human-readable content summarizing a report.*/
@JSExportAll case class ReportPage(
  title: String,
  markdown: String,
  suggestedFileName: String
)

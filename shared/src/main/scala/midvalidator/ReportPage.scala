package edu.holycross.shot.mid.validator

import scala.scalajs.js.annotation._

/** Human-readable content summarizing a report.*/
@JSExportAll case class ReportPage (

  /** Brief title phrase, can be prefixed to markdown text,
  * or used separately on pages linking to this report.*/
  title: String,

  /** Body of report in markdown.  Should begin heading
  * markers at second level, "##".*/
  markdown: String,

  /** Intelligible file name.*/
  suggestedFileName: String,

  /** Should be None for verification tests.*/
  successes: Option[Int],
  
  /** Should be None for verification tests.*/
  failures:  Option[Int],
)

package edu.holycross.shot.mid.validator


import scala.scalajs.js.annotation._


// Implementations of the [[MidEditionType]] trait.
/**  */
@JSExportTopLevel("MidDiplomaticEdition") case object MidDiplomaticEdition extends MidEditionType {
  def label =  "diplomatic"
  def description = "Pure diplomatic reading"
  def versionId = "dipl"
}

@JSExportTopLevel("MidScribalEdition") case object MidScribalEdition extends MidEditionType {
  def label =  "scribal"
  def description = "Edition incorporating scribal corrections and expanding abbreviations"
  def versionId = "scribal"
}

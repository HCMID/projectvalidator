package edu.holycross.shot.mid.validator

/**  */
case object MidDiplomaticEdition extends MidEditionType {
  def label =  "diplomatic"
  def description = "Pure diplomatic reading"
  def versionId = "dipl"
}

case object MidScribalEdition extends MidEditionType {
  def label =  "scribal"
  def description = "Edition incorporating scribal corrections and expanding abbreviations"
  def versionId = "scribal"
}

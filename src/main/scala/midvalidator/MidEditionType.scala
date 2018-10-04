package edu.holycross.shot.mid.validator

/** A named category of token for a given orthography. */
trait MidEditionType {
  def label: String
  def description: String
  def versionId: String
}

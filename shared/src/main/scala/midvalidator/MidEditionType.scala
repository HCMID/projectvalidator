package edu.holycross.shot.mid.validator

/** A named category of edition. */
trait MidEditionType {
  
  /** Human-readable short label.*/
  def label: String

  /** Human-readable descriptive phrase.*/
  def description: String

  /** String value to use for version identifier in
  * `CtsUrn`s of this edition type.
  */
  def versionId: String
}

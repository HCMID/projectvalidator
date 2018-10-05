package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._

/** An orthographic system
*/
trait MidMarkupReader {

  /** Vector of all recognized editionTypes. */
  def editionTypes: Vector[MidEditionType]

  /** Specific edition type to apply. */
  def editionType: MidEditionType

  /**  For a citable node in archival format, compose
  * the a CEX String for the corresponding node in
  * the edition type specified by [[editionType]].
  */
  def editedNode(archival: String, srcUrn: CtsUrn): String
}

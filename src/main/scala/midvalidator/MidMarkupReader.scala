package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._

/** An orthographic system
*/
trait MidMarkupReader {

  /** Vector of all recognized editionTypes. */
  def editionTypes: Vector[MidEditionType]

  /** Specific edition type to apply. */
  def editionType: MidEditionType

  /**
  */
  def edition(archival: String, srcUrn: CtsUrn): String
}

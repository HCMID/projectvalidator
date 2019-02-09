package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import scala.scalajs.js.annotation._


/** Pairing of a text or set of texts identified by URN with an orthographic system.
*
* @param urn Identifier for a text or set of texts.
* @param orthography Orthographic system used in the texts identified by [[urn]].
*/
@JSExportAll  case class OrthoPairing(urn: CtsUrn, orthography: MidOrthography)

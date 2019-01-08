package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._

/** Mapping of a text or group of texts identified by CtsUrn to
* a list of [[MidMarkupReader]]s.
*
* @param urn Text or texts these markup readers can be applied to.
* @param readers Readersa applicable to texts identified by [[urn]].
*/
case class ReadersPairing(urn: CtsUrn, readers: Vector[MidMarkupReader])

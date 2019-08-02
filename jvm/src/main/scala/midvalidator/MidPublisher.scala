package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.scm._

/**
*
* @param library CITE library of content in archival format.
* @param readerMap Mapping of URNs to a Vector of [[MidMarkupReader]]s.
*/
case class MidPublisher(
  library: CiteLibrary,
  readerPairings: Vector[ReadersPairing])  {


    /** For all texts matching a given CtsUrn, use a specified
    * markup reader to publish editions in CEX format.
    *
    * @param urn URN for texts to publish.
    * @param reader Markup reader to use.
    */
    def publishCex(urn: CtsUrn, reader: MidMarkupReader): String = {
      ""
    }
}

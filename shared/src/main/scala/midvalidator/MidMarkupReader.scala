package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

/** A class capable of reading marked up archival
* editions, and creating editions of specified types.
*/
trait MidMarkupReader {

  /** Vector of all recognized editionTypes. */
  def editionTypes: Vector[MidEditionType]

  /** Specific edition type to apply. */
  def editionType: MidEditionType

  /**  For a citable node in archival format, compose
  * the CEX String for the corresponding node in
  * the edition type specified by [[editionType]].
  *
  * @param archival Text contents of a single citable node,
  * in archival representation.
  * @param srcUrn `CtsUrn` for the citable node.
  */
  def editedNode(archival: String, srcUrn: CtsUrn): String



  /**  Given a CEX String for a citable node in archival
  * format, compose the CEX String for the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cexNode CEX String for a single node in
  * archival format.
  */
  def editedNode(cexNode: String):  String = {
    val cols = cexNode.split("#")
    val urn = CtsUrn(cols(0))
    editedNode(cols(1), urn)
  }


  /** Given a CEX String with text contents in
  * archival format, create a CEX String with
  * edition of type [[editionType]].
  *
  * @param cex CEX String of text(s) in archival
  * format.
  */
  def edition(cex: String): String = {
    val cexNodes = for (ln <- cex.split("\n")) yield {
      editedNode(ln)
    }
    cexNodes.mkString("\n")
  }

  /** Given a `Corpus`, create a
  * CEX edition of type [editionType].
  *
  * @param corpus Corpus of text(s) in archival
  * format.
  */
  def edition(corpus: Corpus): String = {
    edition(corpus.nodes)
  }

  /** Given a Vector of `CitableNode`s, create a
  * CEX edition of type [editionType].
  *
  * @param nodes `CitableNode`s with text content
  * in archival format.
  */
  def edition(nodes: Vector[CitableNode]): String = {
    val cexNodes = for (n <- nodes) yield {
      editedNode(n.text, n.urn)
    }
    cexNodes.mkString("\n")
  }
}

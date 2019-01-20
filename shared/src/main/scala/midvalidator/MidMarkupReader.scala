package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

/** A class capable of reading marked up archival
* editions, and creating editions of specified types.
*/
trait MidMarkupReader {

  /** Vector of all recognized editionTypes. */
  def recognizedTypes: Vector[MidEditionType]

  /** Specific edition type to apply. */
  def editionType: MidEditionType


  /**  Given a  citable node in archival
  * format, compose the CEX String for the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cexNode CEX String for a single node in
  * archival format.
  */
  def editedNodeCex(cn: CitableNode):  String

  /**  Given a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cn A single node in archival format.
  */
  def editedNode(cn: CitableNode): CitableNode


  /**  For a citable node in archival format, compose
  * the CEX String for the corresponding node in
  * the edition type specified by [[editionType]].
  *
  * @param archival Text contents of a single citable node,
  * in archival representation.
  * @param srcUrn `CtsUrn` for the citable node.
  */
  def editedNodeCex(srcUrn: CtsUrn, archival: String): String = {
    editedNodeCex(CitableNode(srcUrn, archival))
  }

  /**  For a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param archival Text contents of a single citable node,
  * in archival representation.
  * @param srcUrn `CtsUrn` for the citable node.
  */
  def editedNode(srcUrn: CtsUrn, archival: String): CitableNode = {
    editedNode(CitableNode(srcUrn, archival))
  }

  /**  Given a CEX String for a citable node in archival
  * format, compose the CEX String for the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cexNode CEX String for a single node in
  * archival format.
  */
  def editedNodeCex(cexNode: String):  String = {
    val cols = cexNode.split("#")
    val urn = CtsUrn(cols(0))
    editedNodeCex(urn, cols(1))
  }

  /**  For a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cexNode CEX String for a single node in
  * archival format.
  */
  def editedNode(cexNode: String): CitableNode = {
    val cols = cexNode.split("#")
    val urn = CtsUrn(cols(0))
    editedNode(urn, cols(1))
  }




  /** Given a CEX String with text contents in
  * archival format, create a CEX String with
  * edition of type [[editionType]].
  *
  * @param cex CEX String of text(s) in archival
  * format.
  */
  def editionCex(cex: String): String = {
    val cexNodes = for (ln <- cex.split("\n")) yield {
      editedNodeCex(ln)
    }
    cexNodes.mkString("\n")
  }


  /** Given a corpus in archival format, create a new Corpus
  * of the type specified for this reader.
  *
  * @param corpus A Corpus in archival format.
  */
  def edition(corpus: Corpus) : Corpus = {
    val nodes = for (n <- corpus.nodes) yield {
      editedNode(n)
    }
    Corpus(nodes.toVector)
  }

}

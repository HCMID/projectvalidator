package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

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

  def editedNode(cexNode: String):  String = {
    val cols = cexNode.split("#")
    val urn = CtsUrn(cols(0))
    editedNode(cols(1), urn)
  }

  def edition(cex: String): String = {
    val cexNodes = for (ln <- cex.split("\n")) yield {
      editedNode(ln)
    }
    cexNodes.mkString("\n")
  }

  def edition(nodes: Vector[CitableNode]): String = {
    val cexNodes = for (n <- nodes) yield {
      editedNode(n.text, n.urn)
    }
    cexNodes.mkString("\n")
  }
}

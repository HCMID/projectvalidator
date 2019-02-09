package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._
import edu.holycross.shot.cite._

/** A classified token.
*
* @param urn Exemplar-level CtsUrn identifying the token.
* @param s String value of token.
* @param tokenCategory `None` if string cannot
* be analyzed; otherwise, `Option` of a
* `MidTokenCategory` recognized by an [MidOrthography]'s
* `tokenCategories` function.
*/
@JSExportAll case class MidToken(urn: CtsUrn, string: String, tokenCategory: Option[MidTokenCategory])

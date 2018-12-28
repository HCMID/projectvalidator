package edu.holycross.shot.mid.validator


/** A classified token.
*
* @param s String value of token.
* @param tokenCategory `None` if string cannot
* be analyzed; otherwise, `Option` of a
* `MidTokenCategory` recognized by an [MidOrthography]'s
* `tokenCategories` function.
*/
case class MidToken(s: String, tokenCategory: Option[MidTokenCategory])

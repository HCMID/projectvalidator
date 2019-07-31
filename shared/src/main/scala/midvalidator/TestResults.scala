package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._

/**  Type-paramaterized class for results of testing.
*
* @param good A Vector of successful results of type T.
* @param bad A Vector of error messages.
*/
//@JSExportAll

trait TestResults[T] {
    def good:  Vector[T]
    def bad: Vector[String]
}

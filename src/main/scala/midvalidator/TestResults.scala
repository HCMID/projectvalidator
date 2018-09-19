package edu.holycross.shot.mid.validator

/**  Type-paramaterized class for results of testing.
*
* @param good A Vector of successful results of type T.
* @param bad A Vector of error messages.
*/
case class TestResults[T](good:  Vector[T], bad: Vector[String]) {}

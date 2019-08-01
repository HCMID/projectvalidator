package edu.holycross.shot.mid.validator

/**  Type-paramaterized trait for results of validation testing.
* Classes implementing this trait can be cross-built with the usual
* @JSExportAll annotation.
*/
trait TestResults[T] {

    /** A Vector of successful results of type T. */
    def good:  Vector[T]
    
    /** A Vector of error messages. */
    def bad: Vector[String]

    /** A Markdown String reporting on the testing results.*/
    def mdReport: String
}

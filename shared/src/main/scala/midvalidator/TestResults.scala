package edu.holycross.shot.mid.validator

/**  Type-paramaterized trait for results of validation testing.
* Classes implementing this trait can be cross-built with the usual
* @JSExportAll annotation.
*/
trait TestResults[T] {

    /** True if test was successful */
    def good(observation: T):  Boolean

    /** A Markdown String reporting on the testing results.*/
    def report(observation: T): TestReport
}



import scala.scalajs.js.annotation._

/** Results of testing some artifact.
*
* @param success True if tested object passed the test.
* @param summary Human-readable summary of the test.
*/
@JSExportAll case class TestReport(success: Boolean, summary: String)

package edu.holycross.shot.mid.validator

import scala.scalajs.js.annotation._

/**  Type-paramaterized trait for results of validation testing.
* Classes implementing this trait can be cross-built with the usual
* @JSExportAll annotation.
*/
trait TestResults[T] {

  /** True if test was successful */
  def good(observation: T):  Boolean

  /** A Markdown String reporting on the testing results.*/
  def report(observation: T): TestResult

  /** Get a Vector of parameterized TestResults.*/
  @JSExport def reports(observations: Vector[T]): Vector[TestResult] = {
     observations.map(report(_))
  }


  @JSExport def successes(observations: Vector[T]): Int = {
     val tf = observations.map(good(_))
     (tf.filter(_ ==  true)).size
  }

  @JSExport def failures(observations: Vector[T]): Int = {
     val tf = observations.map(good(_))
     (tf.filter(_ ==  false)).size
  }
}

/** Results of testing a single artifact.
*
* @param success True if tested object passed the test.
* @param summary Human-readable summary of the test.
*/
@JSExportAll case class TestResult(success: Boolean, summary: String)

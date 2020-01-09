package edu.holycross.shot.mid.validator
import scala.scalajs.js.annotation._

import edu.holycross.shot.cite._

@JSExportAll case class PaleographicObservation(reading: CtsUrn, image: Cite2Urn)

/** Implementation of [[TestResults]] for Strings with paleographic observations.
*
* @param cex CEX String for PaleographicObservations.
*/
//@JSExportAll 

abstract class PaleographyResults[T] extends TestResults[T] {

  /** Implementation of required [[TestResults]] function to evaluate
  * a given String as a valid PaleographicObservation.
  *
  * @param paleoCex "#"-delimited String with paleographic observation.
  */
  def report(paleoCex: T):  TestResult = {
    good(paleoCex) match {
      case true =>    {
        paleoCex match {
          case s: String => {
            val cols = s.split("#")
            val observation = PaleographicObservation(CtsUrn(cols(1)), Cite2Urn(cols(2)))
            TestResult(true, "Valid paleographic observation " + observation)
          }
        }

      }
      case false => TestResult(false,"Unable to form valid paleographic observation from String " + paleoCex)
    }
  }

  /** Implement required good function of TestResults trait for
  * String parameter.
  *
  * @param paleoCex "#"-delimited String with paleographic observation.
  */
  def good(paleoCex: T): Boolean = {
    paleoCex match {
      case s: String =>  {
        val cols = s.split("#")
        try {
          val reading = CtsUrn(cols(1))
          val img = Cite2Urn(cols(2))
          // check img for RoI?
          // check text for extended reference?
          true
        } catch {
          case _ : Throwable => false
        }
      }

      case _ => {
        println(s"${paleoCex}  was NOT a string")
        false
      }
    }
  }


/*
  def reportAll:  Vector[TestReport] = {
    val lines : Vector[String] = cex.split("\n").toVector
    val reports = for (ln <- lines) yield {
      val rept = report(T)
      println(rept)
      rept
    }
    reports
  }*/

/*
  def observationResults(
    lines: Vector[String],
    observations: Vector[ObservationResult]) : Vector[ObservationResult] = {
    if (lines.isEmpty) {
      observations

    } else {
      val cols = lines.head.split("#").toVector
      try {
        val reading = CtsUrn(cols(1))
        val img = Cite2Urn(cols(2))
        val observed = observations :+ ObservationResult(Some(PaleographicObservation(reading,img)), "")
        observationResults(lines.tail, observed)

      } catch {
        case t: Throwable =>
        val observed = observations :+ ObservationResult(None, "Unable to validate observation: " + lines.head)
        observationResults(lines.tail, observed)
      }
    }
  } */
}

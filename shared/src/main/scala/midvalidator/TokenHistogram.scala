package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._

/*
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import com.cibo.evilplot.numeric.Point


import com.cibo.evilplot.colors._
import java.awt.Image.SCALE_SMOOTH
*/

import scala.scalajs.js.annotation._
/**
*/
@JSExportAll case class TokenHistogram(tokens: Vector[MidToken]) {


  /** Counts of individual tokens sorted by count. */
  def histogram: Vector[(String, Int)]= {
    val grouped = tokens.groupBy(_.string)
    val counted =  grouped.map{ case (k,v) => (k,v.size) }
    counted.toSeq.sortBy(_._2).reverse.toVector
  }


  /** Histogram limited to entries occurrning n or more times.
  *
  * @param n Minimum count for entries to include.
  */
  def thresholdN(n: Int = 3) = {
    histogram.filter(_._2 >= n)
  }


  /** Histogram containing n most frequent entries.
  *
  * @param n Number of entries to include.
  */
  def topN(n: Int = 100) = {
    histogram.take(n)
  }


  /**  Create evilplot graph of histogram.

  def histoPlot = {
    val data = for ((rec,i) <- histogram.zipWithIndex) yield {
      Point(i, rec._2)
    }
    LinePlot.series(data, "Line graph", HSL(210, 100, 56)).
    yAxis().frame().
    yLabel("Occurrences").render()
  }
  */

}

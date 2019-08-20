package edu.holycross.shot.mid.validator
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import scala.collection.immutable.ListMap
import scala.scalajs.js.annotation._
import edu.holycross.shot.histoutils._
/**
*/
@JSExportAll case class TokenizableCorpus(corpus: Corpus, orthography: MidOrthography) {

  def tokenizedCorpus = orthography.tokenizedCorpus(corpus)

  def wordList : Vector[String] = Vector.empty[String]

  def lexHistogram: Histogram[String] = Histogram(Vector.empty[Frequency[String]])

  def categoryHistogram: Histogram[MidTokenCategory] = Histogram(Vector.empty[Frequency[MidTokenCategory]])

  def concordance: Map[String, CtsUrn] = Map.empty[String,CtsUrn]

}

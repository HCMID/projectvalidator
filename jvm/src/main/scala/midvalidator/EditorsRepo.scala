package edu.holycross.shot.mid.validator

import edu.holycross.shot.scm._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.dse._
import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.citevalidator._

import edu.holycross.shot.mid.markupreader._
import edu.holycross.shot.mid.orthography._

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._

import scala.annotation.tailrec

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

/** A class for working with HC-MID editorial work in a
* local file system laid out according to conventions first
* defined in 2018.  The class includes a function to create
* a CiteLibrary from the contents of these files.
*
* @param baseDir Root directory of repository.
* @param readerMap Mapping of String names to classes of [[MidMarkupReader]], necessary in building CITE library.
*/
case class EditorsRepo(
  baseDir: String,
  readerMap:  Map[String, Vector[MidMarkupReader]]
) extends LogSupport {
    Logger.setDefaultLogLevel(LogLevel.DEBUG)
  /** Directory for DSE records (in CEX format).*/
  val dseDir = File(baseDir + "/dse")
  /** Writable directory for validation reports. */
  val validationDir = File(baseDir + "/validation")
  /** Directory with cataloged editions of texts. */
  val editionsDir = File(baseDir + "/editions")
  /** Directory with cataloged editions of texts. */
  val textConfig = File(baseDir + "/textConfig")
  /** Directory with paleographic observations (in CEX format).
  val paleographyDir = File(baseDir + "/paleography")
  */
  /** Directory with library headers for building composite CEX file.*/
  val libHeadersDir = File(baseDir + "/header")
  /** Directory with TBS data of codices you're editing.*/
  val codicesDir = File(baseDir + "/codices-data")
  /** Directory with CITE Collection cataloging of codices you're editing.*/
  val codicesCatalogs = File(baseDir + "/codices-catalog")
  /** Vector of all required directories for an HCMID editorial project.*/
  val dirs = Vector(dseDir, editionsDir, validationDir,
    /*paleographyDir,*/
    libHeadersDir, codicesDir, codicesCatalogs, textConfig)

  // Insist on required directory layout:
  for (d <- dirs) {
    if (! d.exists) {
      val msg = "Repository not correctly laid out: missing directory  " + d
      warn(msg)
      throw new Exception(msg)
    }
  }


  /** Build a CITE library from the files in this repository. */
  def library: CiteLibrary = {
    // required components:
    // text repo, dse, collections of codices
    CiteLibrary(libHeader + dseCex + rawTextsCex  + codicesCex)
  }

  /** Build [[OrthoPairing]]s from configuration in this repository.
  def orthographies: Vector[OrthoPairing] = {
    val data = orthoConfig.lines.toVector.tail
    val pairings = data.map( str => {
      try {
          val cols = str.split("#")
          val txt = CtsUrn(cols(0))
          val ortho = orthoMap(cols(1))
          OrthoPairing(txt, ortho)

      } catch {
        case t : Throwable => {
          warn("Catastrophe: " + t)
          throw t
        }
      }
    })
    pairings
  }

*/

  /** Use convention that first reader listed for each CTS URN
  * in markup reader configuration must produce a diplomatic edition.
  *
  * @param urn CTS Urn identifying text(s) to find reader for.
  */
  def diplomaticReader(urn: CtsUrn) : MidMarkupReader = {
    val readerMatches = readers.filter(_.urn >= urn)
    require(readerMatches.size == 1, s"Failed to find diplomatic reader.\nURN matched more than one configuration entry: \n\t${urn}")
    readerMatches(0).readers(0)
  }

  /** Build [[ReadersPairing]]s from configuration in this repository.*/
  def readers: Vector[ReadersPairing] = {
    val data = readersConfig.lines.toVector.tail
    val configSplits = data.map(ln => ln.split("#").toVector)
    val configKeys = configSplits.map(v => v(1))
    debug(configKeys)

    val missingKeys = configKeys.toSet diff readerMap.keySet
    if (missingKeys.nonEmpty) {
      val msg = "Catastrophe: " + missingKeys.mkString(",") + " in textConfig/readers.cex missing from map of MarkupReaders."
      warn(msg)
      throw new Exception(msg)
    }

    val pairings = data.map( str => {
      //try {
        val cols = str.split("#")
        val readerStrings = cols(1).split(",").toVector
        val readers = readerStrings.map(s => readerMap(s))
        ReadersPairing(CtsUrn(cols(0)),readers.flatten)
      /*} catch {
        case t: Throwable => {
          warn("Catastrophe: could not find " + t + " in reader pairings from :\n" + data.mkString("\n") )
          throw t
        }
      }*/
    })
    pairings
  }


  /** Extract subcorpora for texts defined in readers pairings.*/
  def subcorpora: Vector[Corpus] = {
    readers.map(_.urn).map(u => rawTexts.corpus ~~ u)
  }


  /** Recursively composite a list of edition corpora into a single corpus.
  *
  */
  @tailrec private def sumEditions(editionList: Vector[Corpus], corpus: Corpus = Corpus(Vector.empty[CitableNode])): Corpus = {
    if (editionList.isEmpty) {
      corpus
    } else {
      sumEditions(editionList.tail, corpus ++ editionList.head)
    }

  }

  def editions: Corpus = {
    val editedTexts = readers.map( r => {
      val subcorpus = rawTexts.corpus ~~ r.urn
      val subordinated = for (reader <- r.readers) yield {
        val corpusEditions = reader.recognizedTypes.map (t => reader.edition(subcorpus, t))
        //reader.edition(subcorpus) // NEED TYPE
        corpusEditions
      }
      subordinated.flatten
    })
    sumEditions(editedTexts.flatten)
  }

  /** Construct DseVector for this repository's records. */
  def dse:  DseVector = {
    // This collects correct results:
    val triplesCex = DataCollector.compositeFiles(dseDir.toString, "cex", dropLines = 1)
    val tempCollection = Cite2Urn("urn:cite2:validate:tempDse.temp:")
    val dseV = DseVector.fromTextTriples(triplesCex, tempCollection)
    dseV
  }

  /** Construct TextRepository. */
  lazy val rawTexts = TextRepositorySource.fromFiles(
    ctsCatalog.toString,
    ctsCitation.toString,
    editionsDir.toString
  )


  /** CEX library header data.*/
  def libHeader:  String = DataCollector.compositeFiles(libHeadersDir.toString, "cex")

  /** CEX data for DSE relations.*/
  def dseCex:  String = {
    val rows = dse.passages.map(_.cex())
    rows.mkString("\n")
  }

  def codicesCex : String = {
    val codexData = DataCollector.compositeFiles(codicesDir.toString, "cex")
    val codexCatalog = DataCollector.compositeFiles(codicesCatalogs.toString, "cex")
    codexCatalog + codexData
  }

  /** CEX data for text editions.*/
  def rawTextsCex: String = {
    rawTexts.cex()
  }

  /** Catalog of edited texts.*/
  val ctsCatalog = nameBetterFile(textConfig,"catalog.cex")
  /** Configuration of citation for local files (in any supported format).*/
  val ctsCitation = nameBetterFile(textConfig,"citation.cex")
  /** Mapping of CtsUrns to MID markup readers.*/
  val readersConfig = textConfig / "readers.cex"
  /** Mapping of CtsUrns to MID orthography system.*/
  val orthoConfig = textConfig / "orthographies.cex"

  for (conf <- Seq(ctsCatalog, ctsCitation,readersConfig,orthoConfig)) {
    require(conf.exists,"Missing required configuration file: " + conf)
  }

  require(readers.nonEmpty, "No markup readers in textConfig/readers.cex matched key set " + readerMap.keySet)
}

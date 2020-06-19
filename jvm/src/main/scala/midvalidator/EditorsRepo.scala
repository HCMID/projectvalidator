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
* @param readerMap Mapping of String names to classes of MidMarkupReader, necessary in building CITE library.
*/
case class EditorsRepo(
  baseDir: String,
  readerMap:  Map[String, Vector[MidMarkupReader]],
  orthoMap: Map[String, MidOrthography] = Map.empty[String, MidOrthography],
) extends LogSupport {
    Logger.setDefaultLogLevel(LogLevel.INFO)
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
    CiteLibrary(libHeader + dseCex + allTexts.cex()  + codicesCex)
  }

  /** Build [[OrthoPairing]]s from configuration in this repository.
  */
  def orthographies: Vector[OrthoPairing] = {
    val data = orthoConfig.lines.toVector.tail
    val configSplits = data.map(ln => ln.split("#").toVector)
    val configKeys = configSplits.map(v => v(1))
    val missingKeys = configKeys.toSet diff orthoMap.keySet
    if (missingKeys.nonEmpty) {
      val msg = "Catastrophe: " + missingKeys.mkString(",") + " in textConfig/orthographies.cex does not match map of MidOrthography classes."
      warn(msg)
      throw new Exception(msg)
    }

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

  /** Build ReadersPairings from configuration in this repository.*/
  def readers: Vector[ReadersPairing] = {
    val data = readersConfig.lines.toVector.tail
    val configSplits = data.map(ln => ln.split("#").toVector)
    val configKeys = configSplits.map(v => v(1))
    debug(configKeys)

    val missingKeys = configKeys.toSet diff readerMap.keySet
    if (missingKeys.nonEmpty) {
      val msg = "Catastrophe: " + missingKeys.mkString(",") + " in textConfig/readers.cex does not match map of MarkupReaders."
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

  /** Extract subcorpora for texts defined in orthography pairings.*/
  def tokencorpora: Vector[Corpus] = {
    orthographies.map(_.urn).map(u => rawTexts.corpus ~~ u)
  }

  /** Compose a tokenized corpus of texts from parent texts defined in orthography pairings.*/
  def tokenized: Corpus = {
    val tokenizedTexts = orthographies.map( ortho => {
      val subcorpus = rawTexts.corpus ~~ ortho.urn
      ortho.orthography.tokenizedCorpus(subcorpus)
    })
    sumCorpora(tokenizedTexts)
  }

  /** Recursively composite a list of edition corpora into a single corpus.
  *
  * @param editionsList List of individually corpora to aggregate.
  * @param corpus Material accumulated in a single corpus so far.
  */
  @tailrec private def sumCorpora(corpusList: Vector[Corpus], corpus: Corpus = Corpus(Vector.empty[CitableNode])): Corpus = {
    if (corpusList.isEmpty) {
      corpus
    } else {
      sumCorpora(corpusList.tail, corpus ++ corpusList.head)
    }
  }

  /** Compose a text catalog for tokenized editions.*/
  def tokenizedCatalog : Catalog = {
    val catalogEntries =  for (pairing <- orthographies) yield {
      val matched = editionsCatalog.texts.filter(_.urn ~~ pairing.urn)
      debug("Number matches for tokenizing: " + matched.size)
      val entries = for (parentEntry <- matched) yield {
        val newUrn = parentEntry.urn.addExemplar(pairing.orthography.exemplarId)
        val exemplarLabel = "Tokenized edition of " + parentEntry.versionLabel.get
        val newCitationScheme = parentEntry.citationScheme + ",token"
        CatalogEntry(
          newUrn, newCitationScheme, parentEntry.lang, parentEntry.groupName, parentEntry.workTitle,
          parentEntry.versionLabel, Some(exemplarLabel), true  )
      }
      entries
    }
    val newCatalog = Catalog(catalogEntries.flatten)
    newCatalog
  }

 def tokenizedRepository = TextRepository(tokenized, tokenizedCatalog)

 def allTexts = editionsRepository ++ tokenizedRepository

  /** Compose text Catalog for generated editions.*/
  def editionsCatalog : Catalog = {
    val rawEntries = rawTexts.catalog.texts
    val catalogEntries =  for (pairing <- readers) yield {
      val matched = rawEntries.filter(_.urn ~~ pairing.urn)
      /*matched.size match {
        case 0 => {
          warn("No matches in raw text corpus for URN " + pairing.urn)
        }
        case 1 => {
          info("Single match for " + pairing.urn)
        }
        case _ => {
          warn("Found multiple matches for " + pairing.urn + "\n" + matched.mkString("\n"))
          warn("Will generate edition for first")
        }
      }*/

      val readerPairings = for (rdr <- pairing.readers) yield {
        val edTypes = for (edType <- rdr.recognizedTypes) yield {
          debug("Apply " + edType + " with extension " + edType.versionExtension)
          val entries = for (raw <- matched) yield {
            val newEditionUrn = raw.urn.addVersion(raw.urn.version + edType.versionExtension)
            debug("New edition URN " + newEditionUrn)
            val versionLabel = edType.description + " for " + raw.versionLabel.get
            debug(versionLabel)
            CatalogEntry(
              newEditionUrn, raw.citationScheme, raw.lang, raw.groupName, raw.workTitle,
              Some(versionLabel), raw.exemplarLabel, true  )
          }
          entries
        }
        edTypes.flatten
      }
      readerPairings.flatten
    }
    Catalog(catalogEntries.flatten)
  }

  /** Create a single Corpus containing all configured editions. */
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
    sumCorpora(editedTexts.flatten)
  }

  /** Create a TextRepository for all configured editions.*/
  def editionsRepository: TextRepository = {
    TextRepository( editions, editionsCatalog)
  }

  /** Construct DseVector for this repository's records. */
  def dse:  DseVector = {
    // This collects correct results:
    val triplesCex = DataCollector.compositeFiles(dseDir.toString, "cex", dropLines = 1)
    val tempCollection = Cite2Urn("urn:cite2:validate:tempDse.temp:")
    val dseV = DseVector.fromTextTriples(triplesCex, tempCollection)
    dseV
  }

  /** Construct a TextRepository from files in local repository. */
  lazy val rawTexts: TextRepository = TextRepositorySource.fromFiles(
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


/*
  def allTexts: Corpus = {
    sumCorpora(editions, tokenizedEditions)
  }
  def allTextsCex: String = {
    val allTexts
  }*/

  /** Catalog of edited texts.*/
  val ctsCatalog = nameBetterFile(textConfig,"catalog.cex")
  /** Configuration of citation for local files (in any supported format).*/
  val ctsCitation = nameBetterFile(textConfig,"citation.cex")
  /** Mapping of CtsUrns to MID markup readers.*/
  val readersConfig = textConfig / "readers.cex"
  /** Mapping of CtsUrns to MID orthography system.*/
  val orthoConfig = textConfig / "orthographies.cex"


  // Require configuration files:
  for (conf <- Seq(ctsCatalog, ctsCitation,readersConfig,orthoConfig)) {
    require(conf.exists,"Missing required configuration file: " + conf)
  }
  require(readers.nonEmpty, "No markup readers in textConfig/readers.cex matched key set " + readerMap.keySet)
  if (orthoMap.nonEmpty) {
    require(orthographies.nonEmpty,"No orthographies configured in textConfig/orthographies.cex matching key set " + orthographies)
  }
}

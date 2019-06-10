package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.cex._

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._


/** Validator helps you manage and maintain the contents of a Homer Multitext
*  project repository.
*
* @param repo Root directory of a repository laid out according to
* conventions of HMT project in 2018.
* @param readers Mapping of URN strings to class implementing MidMarkupReader trait.
* @param orthos Mapping of URN strings to class implementing MidOrthography trait.
*
*/
case class Validator(repo: EditorsRepo, readers: Vector[ReadersPairing], orthos: Vector[OrthoPairing]) {

  /** Create a corpus of XML archival editions.
  */
  def raw:  Corpus = {
    TextRepositorySource.fromFiles(repo.ctsCatalog.toString, repo.ctsCitation.toString, repo.editionsDir.toString).corpus
  }

  /** CEX records for paleographic observations.
  *  We want data lines only, so drop 1 header line.
  */
  def paleoCex = DataCollector.compositeFiles(repo.paleographyDir.toString, "cex", 1)

  /** CEX library header data.*/
  val libHeader = DataCollector.compositeFiles(repo.libHeadersDir.toString, "cex")

  /** CEX data for DSE relations.*/
  val dseCex = DataCollector.compositeFiles(repo.dseDir.toString, "cex")

  /** Construct DseVector for this repository's records. */
  def dse:  DseVector = {
    val records = dseCex.split("\n").filter(_.nonEmpty).filterNot(_.contains("passage#")).toVector

    // This value must agree with header data in header/1.dse-prolog.cex.
    val baseUrn = "urn:cite2:validate:tempDse.temp:"
    val dseRecords = for ((record, count) <- records.zipWithIndex) yield {
      s"${baseUrn}validate_${count}#Temporary DSE record ${count}#${record}"
    }

    if (records.isEmpty) {
      DseVector(Vector.empty[DsePassage])
    } else {
      val srcAll = libHeader + dseRecords.mkString("\n")
      DseVector(srcAll)
    }
  }

  def publishTexts = {
    val editionsDir = nameBetterFile(repo.validationDir,"editions")
    if (editionsDir.exists) {
      editionsDir.delete()
    }
    mkdirs(editionsDir)


    for (txtRdrs <- readers) {
      for (rdr <- txtRdrs.readers) {
        println("Using reader " + rdr )

        val texts = raw ~~ txtRdrs.urn
        val edition = rdr.editionCex(texts.cex("#"))

        val fName = txtRdrs.urn.workComponent + "-" + rdr.editionType.versionId + ".cex"
        val editionFile = editionsDir/fName
        editionFile.overwrite(edition)
      }
    }
  }

}


object Validator {

  /** Construct an Validator object from the path to an
  * editorial repository.
  *
  * @param repoPath Path to repository.
  */
  def apply(repoPath: String, readers: Vector[ReadersPairing], orthos: Vector[OrthoPairing])  : Validator = {
    Validator(EditorsRepo(repoPath), readers, orthos)
  }

  def apply(repoPath: String) : Validator = {
    val rdrs = Vector.empty[ReadersPairing]
    val orths = Vector.empty[OrthoPairing]
    val root = File(repoPath)
    println("REPO ROOT " + root)


    val readerConf = nameBetterFile(root, "editions/readers.csv")

    println("READERS:\n" + readerConf.lines)
    Validator(repoPath, rdrs, orths)

  }


  /** Recursively merge  a list of corpora into a single corpus.
  *
  * @param v List of corpora to merge.
  * @param composite Composite corpus compiled so far.
  */
  def mergeCorpusVector(v: Vector[Corpus], composite: Corpus):  Corpus = {
    if (v.isEmpty) {
      composite
    } else {
      val nextCorpus = composite ++ v.head
      mergeCorpusVector(v.tail, nextCorpus)
    }
  }
}

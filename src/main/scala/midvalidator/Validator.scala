package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import org.homermultitext.edmodel._
import org.homermultitext.hmtcexbuilder._


/** Validator helps you manage and maintain the contents of a Homer Multitext
*  project repository.
*
* @param repo Root directory of a repository laid out according to conventions
* of HMT project in 2018.
*/
case class Validator(repo: EditorsRepo) {

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

}

object Validator {

  /** Construct an Validator object from the path to an
  * editorial repository.
  *
  * @param repoPath Path to repository.
  */
  def apply(repoPath: String) : Validator = {
    Validator(EditorsRepo(repoPath))
  }

}

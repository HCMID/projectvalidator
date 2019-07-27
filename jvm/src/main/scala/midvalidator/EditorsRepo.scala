package edu.holycross.shot.mid.validator

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._


/** Local file system of an HCMID editorial repository
* laid out according to 2018 standards.
*
* @param baseDir Root directory of repository.
*/
case class EditorsRepo(baseDir: String)  {
  /** Directory for DSE records (in CEX format).*/
  val dseDir = File(baseDir + "/dse")
  /** Writable directory for validation reports. */
  val validationDir = File(baseDir + "/validation")
  /** Directory with cataloged editions of texts. */
  val editionsDir = File(baseDir + "/editions")
  /** Directory with paleographic observations (in CEX format).*/
  val paleographyDir = File(baseDir + "/paleography")
  /** Directory with library headers for building composite CEX file.*/
  val libHeadersDir = File(baseDir + "/header")
  /** Vector of all required directories for an HCMID editorial project.*/
  val dirs = Vector(dseDir, editionsDir, validationDir, paleographyDir, libHeadersDir)

  for (d <- dirs) {
    require(d.exists, "Repository not correctly laid out: missing directory " + d)
  }


  /** Catalog of edited texts.*/
  val ctsCatalog = nameBetterFile(editionsDir,"catalog.cex")
  /** Configuration of citation for local files (in any supported format).*/
  val ctsCitation = nameBetterFile(editionsDir,"citation.cex")
  /** Mapping of CtsUrns to MID markup readers.*/
  val readersConfig = editionsDir/"readers.csv"
  /** Mapping of CtsUrns to MID orthography system.*/
  val orthoConfig = editionsDir/"orthographies.csv"

  for (conf <- Seq(ctsCatalog, ctsCitation,readersConfig,orthoConfig)) {
    require(conf.exists,"Missing required configuration file: " + conf)
  }

}

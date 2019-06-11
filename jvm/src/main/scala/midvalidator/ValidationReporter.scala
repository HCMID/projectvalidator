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


/**
*/
case class ValidationReporter(midValidator: Validator) {

  val outputDir = midValidator.repo.validationDir

  // compute these once:
  val dse = midValidator.dse
  val corpus = midValidator.raw
  val paleoResults = PaleographyResults(midValidator.paleoCex)


  /** Select a corpus by page reference.
  *
  * @param pg Page to select texts for.
  * @param dse DseVector to consult for records of
  * texts on page.
  */
  def corpusForPage(pg: Cite2Urn) = {
    val textUrns = dse.textsForTbs(pg).toVector
    val miniCorpora = for (u <- textUrns) yield {
      val merged = corpus ~~ u
      merged
    }
    Validator.mergeCorpusVector(miniCorpora, Corpus(Vector.empty[CitableNode]))
  }

  /** Write suite of markdown reports to validate and
  * verify editorial work on a single page.
  *
  * @param pageUrn
  */
  def validate(pageUrn: String) = {
    try {
      val u = Cite2Urn(pageUrn)
      println("Validating page " + u + "...")

      val dirName = u.collection + "-" + u.objectComponent
      val pageDir = outputDir/dirName
      if (pageDir.exists) {
        pageDir.delete()
      }
      mkdirs(pageDir)

      val pageCorpus = corpusForPage(u)

      val home = StringBuilder.newBuilder
      home.append(s"# Review of ${u.collection}, page ${u.objectComponent}\n\n")
      home.append("## Summary of automated validation\n\n")



      //  DSE validation reporting:
      println("Validating  DSE records...")
      val dseReporter =  DseReporter(u, dse, pageCorpus, midValidator.readers)
      val dseValidMd = dseReporter.dseValidation
      val dseHasErrors: Boolean = dseValidMd.contains("## Errors")
      val dseReport = nameBetterFile(pageDir,"dse-validation.md")
      dseReport.overwrite(dseValidMd)

      if (dseHasErrors) {
        println("There were errors in DSE records.")
        home.append("-  ![errors](${sadImg}) DSE validation: there were errors.  ")

      } else {
        home.append(s"-  ![errors](${okImg}) DSE validation: there were no errors. \n")
      }
      home.append("See [details in dse-validation.md](./dse-validation.md)\n")


      // Text validation reporting
      //val errHeader = "Token#Reading#Error\n"


      home.append("\n\n## Visualizations to review for verification\n\n")

      //  1.  DSE indexing
      val dseCompleteMd = dseReporter.dseCompleteness
      val dseCorrectMd = dseReporter.dseCorrectness
      val dseVerify = nameBetterFile(pageDir, "dse-verification.md")
      val dsePassageMd =
      dseVerify.overwrite(dseCompleteMd + dseCorrectMd)

      // 2. Paleographic observations


      // 3. Named entity tagging


      // OV of text contents
      home.append("\n## Overview of page's text contents\n\n")


      home.append("\nWord data:\n\n")

      home.append("-  frequencies:  see [wordFrequencies.cex](./wordFrequencies.cex)\n")
      home.append("-  index of all occurrences:  see [wordIndex.cex](./wordIndex.cex)\n")
      home.append("-  list of unique words (suitable for morphological analysis):  see [wordlist.txt](./wordlist.txt)\n")


      home.append("\nCharacter data:\n\n")
      home.append("-  frequencies:  see [characterFrequencies.cex](./characterFrequencies.cex)\n")

      val index = nameBetterFile(pageDir,"summary.md")
      index.overwrite(home.toString)


    } catch {
      case t: Throwable => {
        println("Could not validate " + pageUrn)
        println("Full error message:\n\t")
        println(t.toString + "\n\n")
      }
    }
  }

}

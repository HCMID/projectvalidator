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


/**  Manage writing of markdown reports.
*/
case class ValidationReporter(
  editorsRepo: EditorsRepo,
  otherPages: Vector[ReportPage] = Vector.empty[ReportPage]) {

  lazy val reptWriter = ReportWriter(editorsRepo.validationDir)
  lazy val dseOV = DseOverview(editorsRepo.library, editorsRepo.readers)

  def validate(surface: Cite2Urn) : Unit = {
    /*
    val targetDir = editorsRepo.validationDir / s"${surface.collection}-${surface.objectComponent}"

    // DSE is required in version 7.0.0.  Other reports may be added.
    val allReportPages = dseOV.reportPages(surface) ++ otherPages
    reptWriter.writeReports(allReportPages)

    val index = composeIndex(allReportPages, surface)
    val indexPage = targetDir / "index.md"
    indexPage.overwrite(index)

    println(s"Wrote a total of ${allReportPages.size + 1} files to:\n" + targetDir)
    */
  }


  def composeIndex(pages: Vector[ReportPage], surface: Cite2Urn) : String =  {
    val txt = StringBuilder.newBuilder
    txt.append(s"# Reports for ${surface.collection}, ${surface.objectComponent}\n\n")
    val successes = pages.map(_.successes).flatten.sum
    val failures = pages.map(_.failures).flatten.sum

    txt.append("## Summary of automated validation\n\n")

    if (failures == 0){
      txt.append("- ![errors](" + okImg + ") There were no errors.")
    } else {
      txt.append("- ![errors]("+ sadImg + ") There errors.  See details below.")
    }
    txt.append("Tests passed **" + successes + "** / tests failed **" + failures + "**")


    txt.append("\n\n## Detailed reports")
    txt.append("\n\nTotal report files: **" + pages.size + "**\n\n")
    for (pg <- pages) {
      txt.append("- *" + pg.title + "*.")

      pg.successes match {
        case None => {
          txt.append( s" (see [${pg.suggestedFileName}](${pg.suggestedFileName}))\n")
        }
        case _ => {
          txt.append(" Tests passed/failed: ")
          txt.append(pg.successes.get + "/" + pg.failures.get)
          txt.append( s" (see more information in [${pg.suggestedFileName}](${pg.suggestedFileName}))\n")
        }
      }

    }
    txt.toString
  }

/*
reptWriter.writeReports(reptPages)
val targetDir = repo.validationDir / s"${urn.collection}-${urn.objectComponent}"
println(s"Wrote total of ${reptPages.size} reports to:\n" + targetDir)
*/
  //val outputDir = midValidator.repo.validationDir

  // compute these once:
  //def dse = midValidator.dse
  //def corpus = midValidator.raw
  //def paleoResults = PaleographyResults(midValidator.paleoCex)


  /** Select a corpus by page reference.
  *
  * @param pg Page to select texts for.

  def corpusForPage(pg: Cite2Urn) = {
    //These are unsorted!
    val textUrns = dse.textsForTbs(pg).toVector
    println(s"Got ${textUrns.size} text passages, now sorting...")
    println(textUrns.zipWithIndex.mkString("\n"))
    val sorted = midValidator.raw.sortPassages(textUrns)
    println("Sorted, now merge sorted nodes...")

    val miniCorpora = for (u <- sorted) yield {
      val merged = corpus ~~ u
      //println("checked " + u + " against " + corpus.nodes.map(_.urn))
      merged
    }
    //println("Finding nodes for " + pg + ", got " + miniCorpora.filter(_.nonEmpty).size + " nodes.")
    //println("CORPORA: " + miniCorpora.size)
    //println(miniCorpora(0))
    val singleCorpus = Validator.mergeCorpusVector(miniCorpora, Corpus(Vector.empty[CitableNode]))
    //println("SINGLE CORPUS: " + singleCorpus)
    println("Made corpus of " + singleCorpus.size + " nodes.")

    singleCorpus
  }
*/
  def psgView(pageUrn: String) = {
    /*
      try {
        val u = Cite2Urn(pageUrn)
        println("\n\n===>Validating page " + u + "...")

        val dirName = u.collection + "-" + u.objectComponent
        val pageDir = outputDir/dirName
        if (pageDir.exists) {
          pageDir.delete()
        }
        mkdirs(pageDir)

        val pageCorpus = corpusForPage(u)
        //        println("Corpus for " + u + " = " + pageCorpus.size + "citable nodes.")

        val psgView = nameBetterFile(pageDir, "transcription.md")
        val psgViewHdr = s"# Diplomatic transcription of ${u.collection}, page ${u.objectComponent}\n\n"

        val dseReporter =  DseReporterDeprecated(u, dse, pageCorpus, midValidator.readers)

        val xcription = dseReporter.passageView
        psgView.overwrite(psgViewHdr + xcription)

        } catch {
          case t: Throwable => {
            println("Could not validate " + pageUrn)
            println("Full error message:\n\t")
            println(t.toString + "\n\n")
          }
        }*/
  }

/*
  def directoryForSurface(surf: Cite2Urn) : File = {
    val dirName = surf.collection + "-" + surf.objectComponent
    val surfaceDirectory = outputDir/dirName
    if (surfaceDirectory.exists) {
      surfaceDirectory.delete()
    }
    mkdirs(surfaceDirectory)
    surfaceDirectory
  }*/

  /** Write suite of markdown reports to validate and
  * verify editorial work on a single page.
  *
  * @param pageUrn
  */
  def validate(pageUrn: String) = {
  /*  try {
      val u = Cite2Urn(pageUrn)
      println("\n\n===>Validating page " + u + "...")
      println("Selecting corpus...")
      // THIS IS FAILING:
      val pageCorpus = corpusForPage(u)
      println("Getting DSE reporter...")
      val dseReporter =  DseReporterDeprecated(u, dse, pageCorpus, midValidator.readers)
      println("Checking valid DSE...")
      val dseValidMd = dseReporter.dseValidation
      println("Composing passage view")
      val xcription = dseReporter.passageView

      val pageDir = directoryForSurface(u)
      //  DSE validation reporting:
      val dseReport = nameBetterFile(pageDir,"dse-validation.md")
      dseReport.overwrite(dseValidMd)
      //  1.  DSE indexing
      val dseCompleteMd = dseReporter.dseCompleteness
      val dseCorrectMd = dseReporter.dseCorrectness
      val dseVerify = nameBetterFile(pageDir, "dse-verification.md")
      val dsePassageMd =
      dseVerify.overwrite(dseCompleteMd + dseCorrectMd)

      // Passage verification
      val psgView = nameBetterFile(pageDir, "transcription.md")
      val psgViewHdr = s"# Diplomatic transcription of ${u.collection}, page ${u.objectComponent}\n\n"
      psgView.overwrite(psgViewHdr + xcription)



      // BUILD UP HOME PAGE:
      val home = StringBuilder.newBuilder
      home.append(s"# Review of ${u.collection}, page ${u.objectComponent}\n\n")
      home.append("## Summary of automated validation\n\n")
      val dseHasErrors: Boolean = dseValidMd.contains("## Errors")
      if (dseHasErrors) {
        println("\nThere were errors in DSE records.\n")
        home.append(s"-  ![errors](${sadImg}) DSE validation: there were errors.  ")

      } else {
        home.append(s"-  ![errors](${okImg}) DSE validation: there were no errors. \n")
      }
      home.append("See [details in dse-validation.md](./dse-validation.md)\n")
      home.append("\n\n## Visualizations to review for verification\n\n")
      home.append("- verify DSE completeness: [dse-verification.md](./dse-verification.md)\n")
      home.append("- verify correctness of [diplomatic transcription](./transcription.md)\n")











      // 2. Paleographic observations


      // 3. Named entity tagging


      // OV of text contents
      home.append("\n## Overview of page's text contents\n\n")

      home.append("TBA\n\n")

      home.append("\nWord data:\n\n")

      home.append("-  frequencies:  see [wordFrequencies.cex](./wordFrequencies.cex)\n")
      home.append("-  index of all occurrences:  see [wordIndex.cex](./wordIndex.cex)\n")
      home.append("-  list of unique words (suitable for morphological analysis):  see [wordlist.txt](./wordlist.txt)\n")


      home.append("\nCharacter data:\n\n")
      home.append("-  frequencies:  see [characterFrequencies.cex](./characterFrequencies.cex)\n")

      val index = nameBetterFile(pageDir,"summary.md")
      println("Writing summary in " + index + "\n\n")
      index.overwrite(home.toString)


    } catch {
      case t: Throwable => {
        println("Could not validate " + pageUrn)
        println("Full error message:\n\t")
        println(t.toString + "\n\n")
      }
    }*/
  }

}

package edu.holycross.shot.mid.validator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.cex._




/** An object for analyzing and writing reports about the DSE
* relations for material on a single surface (page).
*
* @param pg Page to analyze.
* @param dse DSE records for the whole repository.
* @param txts Corpus composed only of texts on this page.
*/
case class DseReporter(pg:  Cite2Urn, dse: DseVector, txts: Corpus, readers: Vector[ReadersPairing]) {

  //require(txts.size > 0, "Can not create DseReporter:  no texts found for " + pg)

  /** Check if passages in a list are actually in corpus
  * or not, and create a Vector of missing passages.
  *
  * @param txts Passages to check.
   */
  def missingPassages:  Vector[CtsUrn] = {
    val psgs = dse.textsForTbs(pg)
    if (txts.nodes.isEmpty) {
      psgs
    } else {

      val accountedFor = for (psg <- psgs ) yield {
        val matches = txts ~~ psg
        if (matches.isEmpty) {
          Some(psg)
        } else {
          None
        }
      }
      accountedFor.flatten
    }
  }


  /** Compose markdown report on automated validation of DSE records.
  */
  def dseValidation: String = {
    val md = StringBuilder.newBuilder
    val errors = StringBuilder.newBuilder

    md.append(s"# Automated validation of DSE records for ${pg.collection}, page " + pg.objectComponent + "\n\n")
    if (dse.size == 0) {
      md.append("## Errors\n\nNo DSE records found!\n\n")
    } else {

      val dseImgMessage = dse.imageForTbs(pg)
      md.append("\n\n## Reference image\n\n" +  dseImgMessage  + "\n\n")

      val dseTextMessage =  if (missingPassages.isEmpty) {
        "**All** passages indexed in DSE records appear in text corpus."

      } else {
        "### Errors\n\nThere were errors indexing texts. \n\n" +
        "The following passages in DSE records do not appear in the text corpus:\n\n" + missingPassages.map("-  " + _ ).mkString("\n") + "\n\n"

      }
      md.append("\n\n## Relation of DSE records to text corpus\n\n" +  dseTextMessage  + "\n\n" + errors.toString + "\n\n")

    }

    md.toString
  }

  case class IndexedNode(citableNode: CitableNode, index: Int)
  def indexedNodes : Vector[IndexedNode] = {
    val allIndexed = txts.nodes.zipWithIndex
    for (n <- allIndexed) yield {
      IndexedNode(n._1, n._2)
    }
  }

  /** Given an unsorted Vector of CtsUrns, sort them against the corpus,
  * and create a sorted Vector mapping CtsUrns to IndexedNodes.

  def sortTextNodes(unsorted: Vector[CtsUrn]) :  Vector[(CtsUrn, Option[IndexedNode])] = {
    val allIndexed = indexedNodes
    val selectionIndexed = for (ref <- unsorted) yield {
      val matches = allIndexed.filter(_.  citableNode.urn == ref)
      matches.size match {
        case 1  => (ref, Some(matches(0)))
        case _ => (ref, None)
      }

    }
    //println("\nIndexed selection: " + selectionIndexed.mkString(", "))
    val sorted = selectionIndexed.sortBy(_._2.get.index)
    //println("\nSORTED: " + sorted.mkString(", "))
    sorted
  }
  */


  def passageMarkdown(urn: CtsUrn) : String = {
    val imgmgr = ImageManager()
    val matches = txts ~~ urn
    matches.size match {
      case 0 =>  "**No transcription for " +  urn + "**"

      case 1 => {
        val cn = matches.nodes(0)
        val img = dse.imageWRoiForText(cn.urn)
        val md = imgmgr.markdown(img, 1000)
        val allApplicable = (readers.filter(_.urn ~~ cn.urn))
        if (allApplicable.isEmpty) {
          "**No reader configured for " +  cn.urn + "**" + "  " + md
        }  else {
          val applicable = allApplicable.head.readers.head
          applicable.editedNode(cn).text +  " (*" + cn.urn + "*)" + "  " + md
        }
      }

      case _ => "**Multiple text nodes found for " + urn + "**"
    }
  }

  /**  Compose markdown content juxtaposing indexed image with
  * transcribed text content for a specific page.
  */
  def passageView : String = {

    val urns = dse.textsForTbs(pg)
    //println("TEXTS FOR " + pg + ": " + urns.mkString(", "))
    val psgs = txts.sortPassages(urns)
    //println("SORTED URNs: " + psgs.mkString(", "))
    //println("\n\n\nPassageView:")



    val rows = for (psg <- psgs) yield {
      passageMarkdown(psg)
    //val rows = for (psg <- urns) yield {

      //val psgOpt = psg._2
      //println("pasageView:  psg urn " + psgUrn)
      //println("\topt: " + psgOpt)


/*
      val psgMd = psgOpt match {

        case None => {
          val msg = "**No transcription for " +  psgUrn + "**"
          println(msg)
          msg
        }

        case _ => {
          val cn = psgOpt.get.citableNode

          // md = "MAKE MD FOR " + cn
          val img = dse.imageWRoiForText(cn.urn)
          val md = imgmgr.markdown(img, 1000)
          val allApplicable = (readers.filter(_.urn ~~ cn.urn))
          if (allApplicable.isEmpty) {
            "**No reader configured for " +  cn.urn + "**" + "  " + md
          }  else {
            val applicable = allApplicable.head.readers.head
            applicable.editedNode(cn).text +  " (*" + cn.urn + "*)" + "  " + md
            //applicable.editedNode(psgNodes(0)).text +  " (*" + psg + "*)" + "  " + md
          }

          //"CN " + cn
        }
      }*/
      //psgMd
    }
    rows.mkString("\n\n\n")
  }

  /** Compose markdown report to verify correctness of DSE records.
  *
  * @param pg Page to analyze.
  */
  def dseCorrectness:  String = {

    val bldr = StringBuilder.newBuilder
    bldr.append("\n\n### Correctness\n\n")
    bldr.append("To check for **correctness** of indexing, please verify that text transcriptions and images agree:\n\n")

    bldr.append(passageView)
    bldr.toString
    ""
  }



  /** Compose markdown report to verify completeness of DSE records.
  *
  * @param pg Page to analyze.
  */
  def dseCompleteness: String = {
    val bldr = StringBuilder.newBuilder
    bldr.append(s"\n\n## Verification of DSE records for ${pg.collection}, page ${pg.objectComponent}\n\n###  Completeness\n\n")
    bldr.append(s"To check for **completeness** of coverage, please review these visualizations of DSE relations in ICT2:\n\n")
    bldr.append(s"- [**all** DSE relations of page ${pg.objectComponent} ](${dse.ictForSurface(pg)}).\n\n")

    bldr
    .append("Visualizations for individual documents:\n\n")
    val texts =  dse.textsForTbs(pg).map(_.dropPassage).toVector
    val txt = texts(0)
    println("Create view for " + txt + " ...")
    val oneDocDse = DseVector(dse.passages.filter(_.passage ~~ txt))
    val viz = "-  all [passages in " + txt + "](" + oneDocDse.ictForSurface(pg) + ")."

    bldr.append(viz + "\n")
    bldr.toString
  }
}

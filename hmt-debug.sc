//import edu.holycross.shot.citevalidator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.markupreader._

import org.homermultitext.edmodel._


def readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "DiplomaticReader" ->   Vector(DiplomaticReader)
)

val repo = EditorsRepo("iliad23-2020", readerMap)
val lib = repo.library
val c = library.textRepository.get.corpus
val diplomatic = DiplomaticReader.edition(c, MidDiplomaticEdition)

// Use dipl. reader to get new text:


/*
val recto = Cite2Urn("urn:cite2:hmt:msB.v1:303r")
val verso = Cite2Urn("urn:cite2:hmt:msB.v1:303v")
val dseValidator = DseValidator(lib)
val townley = Cite2Urn("urn:cite2:hmt:burney86pages.v1:250r")
val pageDse = dseValidator.dsev.passages.filter(_.surface == townley)
val ordered = dseValidator.corpus.sortPassages(pageDse.map(_.passage)).filter(_ ~~ CtsUrn("urn:cts:greekLit:tlg0012:"))
//val allValidators = Vector(dseValidator)
*/

/*
val dseFile = "jvm/src/test/resources/bifoliosample/dse/e3_dse.cex"
val scholiaFile = "jvm/src/test/resources/bifoliosample/dse/scholia_e3.cex"
val dummyCollection = Cite2Urn("urn:cite2:units:dummy.v1:")
val scholdse = DseSource.fromTriplesFile(scholiaFile,dummyCollection)
*/

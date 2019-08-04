import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._

// Normally, just "." in an MID project
val repoRoot = "jvm/src/test/resources/simplelatin"


// Standard way to validate:
// 1. define readers your project uses
val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
)

// 2. define orthographies your project uses
val orthoMap : Map[String, MidOrthography] = Map(
  "Latin23" -> Latin23
)

// 3. Build a validator. This requires ortho map as well as a CITE library.
val repo = EditorsRepo(repoRoot, readerMap, orthoMap)

// 4. To write reports to a File, create a ReportWriter (JVM only)
val reptWriter = ReportWriter(repo.validationDir)


def validate(urnStr: String) : Unit = {
  try {
    val urn = Cite2Urn(urnStr)

    /// Add all your reports here:
    val dseOV = DseOverview(repo.library)
    val reptPages = dseOV.reportPages(urn)  // ++ any others...

    reptWriter.writeReports(reptPages)
    val targetDir = repo.validationDir / s"${urn.collection}-${urn.objectComponent}"
    println(s"Wrote total of ${reptPages.size} reports to:\n" + targetDir)
  } catch {
    case t: Throwable => {
      println("Well, that didn't work.  Error was:  " + t)
    }
  }

}
val pg = "urn:cite2:ecod:sg359pages.v1:36"

println("\n\nTo validate a page:\n")
println("\tvalidate(URN_STRING)\n\n")
println("(e.g., try validate(pg))")


/* TBA ...

//val validator = MidValidator(repo.library, repo.orthographies)
// then run "validate" function on a surface or surfaces defined by URN


def publish: Unit = {
  val publisher = MidPublisher(repo.library, repo.readers)
  // then run "publish"
}*/

import edu.holycross.shot.mid.validator._

// Normally, just "." in an mID project
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



def validate : Unit = {
  val validator = MidValidator(repo.library, repo.orthographies)
  // then run "validate" function on a surface or surfaces defined by URN
}

def publish: Unit = {
  val publisher = MidPublisher(repo.library, repo.readers)
  // then run "publish"
}

import edu.holycross.shot.mid.validator._

// Normally, just "." in an mID project
val repoRoot = "jvm/src/test/resources/twins-alpha"


// Standard way to validate:
// 1. define readers your project uses
val readerMap : Map[String, Vector[MidMarkupReader]] =  Map("any" -> Vector.empty[MidMarkupReader])
// Syntax error here:
// Map(  "MidProseABReader" ->   Vector(MidProseABReader))

// 2. define orthographies your project uses
val orthoMap : Map[String, MidOrthography] = Map(
  "Latin23" -> Latin23
)

// 3. Build a validator
val repo = EditorsRepo(repoRoot, readerMap, orthoMap)
val validator = MidValidator(repo.library, repo.orthographies)

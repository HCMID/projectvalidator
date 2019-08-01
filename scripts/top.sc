import edu.holycross.shot.mid.validator._

// Normally, just "." in an mID project
val repoRoot = "jvm/src/test/resources/twins-alpha"

//Standard way to validate:
val repo = EditorsRepo(repoRoot)
val validator = MidValidator(repo.library)

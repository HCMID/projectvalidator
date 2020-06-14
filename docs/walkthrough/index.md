---
title: Walk through an example
layout: page
---

**Library version 13.0.0**


Import all the libraries we'll use:

```scala
// Import the generic validator library:
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

import edu.holycross.shot.citevalidator._
import edu.holycross.shot.mid.markupreader._
import edu.holycross.shot.mid.orthography._
```

In this example, we'll use a single markup reader, and create an `EditorsRepo`
from the directory `chantsample`:




```scala
val readerMap =   Map(
  "DiplomaticReader" -> Vector(MidProseABDiplomatic)
)
// readerMap: Map[String, Vector[MidProseABDiplomatic.type]] = Map(
//   "DiplomaticReader" -> Vector(
//     edu.holycross.shot.mid.markupreader.MidProseABDiplomatic$@6df53aeb
//   )
// )
val repo = EditorsRepo("jvm/src/test/resources/chantsample", readerMap)
// repo: EditorsRepo = EditorsRepo(
//   "jvm/src/test/resources/chantsample",
//   Map(
//     "DiplomaticReader" -> Vector(
//       edu.holycross.shot.mid.markupreader.MidProseABDiplomatic$@6df53aeb
//     )
//   )
// )
```

The `EditorsRepo` assembles the CITE library we need:

scala mdoc
```
val lib = repo.library
```

For this example, we'll apply a single validator:

scala mdoc:silent
```
val dseValidator = DseValidator(lib)
val validators = Vector(dseValidator)
```

Now we can use the `LibraryValidator` object to validate a page in the library using the given list of validators:


scala mdoc
```
val pg = Cite2Urn("urn:cite2:hmt:msA.v1:292v")
val rslts = LibraryValidator.validate(pg,validators)
```

The resulting Vector of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.

---
title: Walk through an example
layout: page
---

**Library version @VERSION@**


Import all the libraries we'll use:

```scala mdoc
// Import the generic validator library:
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._

// Import markup readers for this project:
import org.homermultitext.edmodel._
```

In this example, we'll use a single markup reader, and create an `EditorsRepo`
from the directory `hmtexample`:

```scala mdoc
val readerMap =   Map(
  "DiplomaticReader" -> Vector(DiplomaticReader)
)
val repo = EditorsRepo("examplerepo", readerMap)
```

The `EditorsRepo` assembles the CITE library we need:

```scala mdoc
val lib = repo.library
```

For this example, we'll apply a single validator:

```scala mdoc
val dseValidator = DseValidator(lib)
val validators = Vector(dseValidator)
```

Now we can use the `LibraryValidator` object to validate a page in the library using the given list of validators:

```scala mdoc
val pg = Cite2Urn("urn:cite2:hmt:msA.v1:292v")
val rslts = LibraryValidator.validate(pg,validators)
```

The resulting Vector of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.

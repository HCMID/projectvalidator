---
title: Walk through an example
layout: page
---

**Library version @VERSION@**


Import all libraries we'll use:

```scala mdoc
// Generic libraries we always use:
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._

// For project-specific markup reader:
import org.homermultitext.edmodel._
```

In this example, we'll use a single markup reader, and create an `EditorsRepo`
from the directory `hmtexample`:

```scala mdoc

val readerMap =   Map(
  "DiplomaticReader" ->   Vector(DiplomaticReader)
)

val repo = EditorsRepo("hmtexample",
readerMap)
```

The `EditorsRepo` assembles the CITE library we need:

```scala mdoc
val lib = repo.library
```

For this example, we'll create a Vector with a single validator:

```scala mdoc
val dseValidator = DseValidator(lib)
val validators = Vector(dseValidator)
```

Now we can use the `LibraryValidator` to validate a page in the library using the given list of validators:

```scala mdoc
val pg = Cite2Urn("urn:cite2:hmt:msA.v1:292v")
val rslts = LibraryValidator.validate(lib,validators,pg)
```

The resulting Vetor of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.

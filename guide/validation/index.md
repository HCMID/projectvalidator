---
title: Walk through an example
layout: page
---

**Library version @VERSION@**


## 1. Build a CITE library

Import the libraries we'll use:


```scala mdoc
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.mid.markupreader._
```

In this example, we'll use a single markup reader, and create an `EditorsRepo`
from the directory `chantsample`.




```scala mdoc
val readerMap =   Map(
  "MidProseAB" -> Vector(MidProseABDiplomatic)
)
val repo = EditorsRepo("jvm/src/test/resources/chantsample", readerMap)
```

The `EditorsRepo` assembles the CITE library we need:


```scala mdoc:silent
val lib = repo.library
```


## 2. Validate a page in the library

For this example, we'll apply a single validator:


```scala mdoc:silent
val dseValidator = DseValidator(lib)
val validators = Vector(dseValidator)
```

Now we can use the CITE `LibraryValidator` object to validate a page in the library using the given list of validators.

Create a URN for the page to validate:

```scala mdoc:silent
import edu.holycross.shot.cite._
val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
```

```scala mdoc
val rslts = LibraryValidator.validate(pg, validators)
```

The resulting Vector of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.

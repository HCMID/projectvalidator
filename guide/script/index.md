---
layout: page
title: Markup readers
---

**Library version @VERSION@**


> Add a complete MID validation script here!

A script to validate MID work in progress should do these things.



## Import required libraries

You always need these:

```scala mdoc
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.mid.markupreader._
import edu.holycross.shot.cite._
```

## Define markup readers

Define the markup reader classes to apply for a given key in `textConfig/readers.cex`:

```scala mdoc
def readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "MidProseAB" ->   Vector(MidProseABDiplomatic)
)
```

## Create an `EditorsRepo`

Give it the path to the root directory, and the map of markup readers you created..  (You might just use `"."` and require the script to be run from the repository root.)

```scala mdoc:invisible
val repoRoot = "jvm/src/test/resources/chantsample"
```
```scala mdoc:silent
val repo = EditorsRepo(repoRoot, readerMap)
```

## Define appropriate validators for your project

*All* MID projects require DSE validation.  You can add as many further validators as are appropriate for the particular content of your project to the Vector of validators.


```scala mdoc:silent
val dseValidator = DseValidator(repo.library)
val validators = Vector(dseValidator)
```


## Define a simple validation function

You'll
```scala mdoc
def validate(pageUrn: String) = {
  val pg = Cite2Urn(pageUrn)  
  LibraryValidator.validate(pg, validators)
}
```

```scala mdoc
validate("urn:cite2:ecod:eins121pages.v1:21")
```

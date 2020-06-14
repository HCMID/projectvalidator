---
layout: page
title: Markup readers
---

**Library version 13.0.0**


> Add a complete MID validation script here!

A script to validate MID work in progress should do these things.


Always need these:

```scala
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.mid.markupreader._
```

## Define markup readers

Define the markup reader classes to apply for a given key in `editions/readers.cex`:

```scala
val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "MidProseAB" ->   Vector(MidProseABDiplomatic)
)
// readerMap: Map[String, Vector[MidMarkupReader]] = Map(
//   "MidProseAB" -> Vector(
//     edu.holycross.shot.mid.markupreader.MidProseABDiplomatic$@160aef4b
//   )
// )
```

## Create an `EditorsRepo`

Give it a root directory

```scala
val repo = EditorsRepo(repoRoot, readerMap)
```

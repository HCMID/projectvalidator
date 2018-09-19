---
layout:  page
title: "MID validator library: quick start"
---


## Instantiate a validator


Import the library, create an editorial repository object, and use that to create a validator for the repository:

```scala
scala> import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.validator._

scala> val repo = EditorsRepo("src/test/resources/il10")
repo: edu.holycross.shot.mid.validator.EditorsRepo = EditorsRepo(src/test/resources/il10)

scala> val midValidator = Validator(repo)
midValidator: edu.holycross.shot.mid.validator.Validator = Validator(EditorsRepo(src/test/resources/il10))
```

## Validating paleography

## Validating DSE relations

Get a DSE Vector from a `Validator` instance:

```scala
scala> val dseV = midValidator.dse
dseV: edu.holycross.shot.dse.DseVector = DseVector(Vector(DsePassage(urn:cite2:validate:tempDse.temp:validate_0,Temporary DSE record 0,urn:cts:greekLit:tlg0012.tlg001.e3:10.1,urn:cite2:hmt:e3bifolio.v1:E3_128v_129r,urn:cite2:hmt:e3.v1:128v), DsePassage(urn:cite2:validate:tempDse.temp:validate_1,Temporary DSE record 1,urn:cts:greekLit:tlg0012.tlg001.msB:10.1,urn:cite2:hmt:vbbifolio.v1:vb_128v_129r,urn:cite2:hmt:msB.v1:128v)))
```

---
layout:  page
title: "MID validator library: quick start"
---


## Instantiate a validator


Import the library, create an editorial repository object, and use that to create a validator for the repository:

```scala
scala> import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.validator._

scala> val repo = EditorsRepo("src/test/resources/iliad10")
repo: edu.holycross.shot.mid.validator.EditorsRepo = EditorsRepo(src/test/resources/iliad10)

scala> val midValidator = Validator(repo)
midValidator: edu.holycross.shot.mid.validator.Validator = Validator(EditorsRepo(src/test/resources/iliad10))
```

## Validating paleography

## Validating DSE relations

Get a DSE Vector from a `Validator` instance:

```scala
scala> val dseV = midValidator.dse
dseV: edu.holycross.shot.dse.DseVector = DseVector(Vector(DsePassage(urn:cite2:validate:tempDse.temp:validate_175,Temporary DSE record 175,urn:cts:greekLit:tlg0012.tlg001.msA:10.177,urn:cite2:hmt:vaimg.2017a:VA129VN_0632@0.4805,0.2502,0.4224,0.0255,urn:cite2:hmt:msA.v1:129v), DsePassage(urn:cite2:validate:tempDse.temp:validate_799,Temporary DSE record 799,urn:cts:greekLit:tlg5026.msA.hmt:10.2013,urn:cite2:hmt:vaimg.2017a:VA130RN_0302@0.14,0.7881,0.665,0.0323,urn:cite2:hmt:msA.v1:130r), DsePassage(urn:cite2:validate:tempDse.temp:validate_1213,Temporary DSE record 1213,urn:cts:greekLit:tlg0012.tlg001.vb:10.7,urn:cite2:hmt:vbbifolio.v1:vb_129v_130r@0.2181,0.2914,0.2196,0.02496,urn:cite2:hmt:msB.v1:129v), DsePassage(urn:cite2:validate:tempDse.temp:validate_998,Temp...
```

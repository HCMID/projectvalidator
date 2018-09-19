---
layout:  page
title: "MID validator library: quick start"
---


## Instantiate a validator


Import the library, create an editorial repository object, and use that to create a validator for the repository:

```tut
import edu.holycross.shot.mid.validator._

val repo = EditorsRepo("src/test/resources/iliad10")
val midValidator = Validator(repo)
```

## Validating paleography

## Validating DSE relations

Get a DSE Vector from a `Validator` instance:

```tut
val dseV = midValidator.dse
```

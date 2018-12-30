---
layout:  page
title: "Objects created by the validator"
---




## Instantiate a validator


First import the library, and create an editorial repository object.


```tut
import edu.holycross.shot.mid.validator._

val repo = EditorsRepo("src/test/resources/iliad10")
```

Then create a validator for the repository:

```tut
val midValidator = Validator(repo)
```


## Working with DSE relations

You can get a DSE Vector from a `Validator` instance:

```tut
val dseV = midValidator.dse
```


## Validating paleography

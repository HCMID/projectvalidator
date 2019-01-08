---
layout:  page
title: "Objects created by the validator"
---




## Instantiate a validator



```tut:silent
import edu.holycross.shot.mid.validator._

val repo = EditorsRepo("src/test/resources/iliad10")
val midValidator = Validator(repo)
```


You can get a DSE Vector directly from a `Validator` instance:

```tut
val dseV = midValidator.dse
```

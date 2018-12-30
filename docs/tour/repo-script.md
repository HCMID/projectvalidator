---
layout:  page
title: "Scripting an editorial repository"
---



First import the library.  Create an editorial repository object from  a String identifying the root directory of the editorial repository.


```scala
scala> import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.validator._

scala> val repo = EditorsRepo("src/test/resources/iliad10")
repo: edu.holycross.shot.mid.validator.EditorsRepo = EditorsRepo(src/test/resources/iliad10)
```

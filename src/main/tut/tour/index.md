---
layout: page
title: A hands-on tour of the library
---

Obviously, import the library.

```tut:silent
import edu.holycross.shot.mid.validator._
```



## The editorial repository


Everything starts with an `EditorsRepo`.  You can create one from a relative or absolute path (as a String) to the root of the repository.

```tut:silent
val repo = EditorsRepo("src/test/resources/iliad10")
```

This class enforces MID conventions for organizing data in an editorial project, and will throw an Exception if your repository is missing any required content.


## Validating a repository

A `Validator` object is created from an `EditorsRepo`.

```tut:silent
val midValidator = Validator(repo)
```

The `Validator` analyzes and can validate the contents of the editorial repository.


## Reporting results of validation

A `ValidationReporter` can compose a variety of reports in markdown format.  You create a `ValidationReporter` with a `Validator` object. Pass a URN value (as a String) for a text-bearing surface to its `validate` function, and the `ValidationReporter` will write the resulting reports to the required `validation` directory of the editorial repository.


```tut:silent
val reporter = ValidationReporter(midValidator)
reporter.validate("urn:cite2:hmt:msA.v1:126r")
```



##  Specific forms of validation




-  A `Validator` object can construct a `DseVector` and validate **DSE relations**.  [Example](dse).
-  A `Validator` object can directly validate **paleographic observations**.
-  A `Validator` object uses an implementation of the `MidMarkupReader` trait to validate a **physical document's format** by creating an edition of the kind  specified by an `MidEditionType`.  This is achieved by giving the validator a map of URN values to a non-empty Vector of `MidMarkupReaders`.
-  A `Validator` object uses an implementation of the `MidOrthography` trait to validate the **orthography** of an edition.  This is achieved by giving the validator a map of URN values to a non-empty Vector of `MidMarkupReaders`.
-  Editions with valid orthography are **analyzed as a sequence of classified tokens**.

In many cases, tokens would then be subjected to further, type-specific analysis and validation.

## Nifty utilities

The `projectvalidator` package object has a useful [function that recursively extracts text nodes from an XML node](utils) parsed by Scala.

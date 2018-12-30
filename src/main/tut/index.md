---
layout:  page
title: MID project validator
---


## Links


-   [Quick start](quick)
-   [API docs](api/edu/holycross/shot/mid/validator/index.html):  version **1.3.0**
-   [Github repository](https://github.com/HCMID/projectvalidator)


## About

A code library for validating contents of scholarly editions following conventions defined for Holy Cross  MID Club projects.


## Overview


Implemented and unified in the `Validator` class:

-  The `EditorsRepo` class enforces MID conventions for organizing data in an MID editorial project.
-  The `Validator` class validates the contents of an `EditorsRepo`.
-  A `Validator` object can construct a `DseVector` and validate DSE relations.


Implemented by not fully integrated:

-  Implementations of the `MidEditionType` trait define kinds of editions that can be generated from this repository.
-  Classes implementing the `MidMarkupReader` trait provide validation of textual content.  `MidMarkupReader` instances are created for specific `MidEditionType`s.


Orthography:

An orthography can tokenize strings.

- `MidToken` objects classify strings as belonging to `MidTokenTypes`.

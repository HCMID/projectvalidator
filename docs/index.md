---
title: HCMID validator library
layout: page
---

**Library version 13.0.0**

## `EditorsRepo`

The `EditorsRepo` class assembles a CITE library from an editorial repository with directories layed out following MID club conventions.



- [configuring markup readers](./readers/)
- [conventions for data organization](./directories/)





## Validating work in progress at HC MID

In order to use the `LibraryValidator` with work in progress, we first need to generate a CITE Library.  This requires two things.  First, a further generalized concept:

- [markup readers](./readers/).  Implementations of the `MidMarkupReader` trait define how citable texts formatted in a given system (perhaps using XML markup, or follwing defined conventions in a plain-text in a format like Markdown) can be interpreted in the OHCO2 model. One important use-case for these classes is to translate between one format implementing the OHCO2 model and another.  (For example, you could use an `MidMarkupReader` to read an XML text and write out a plain-text representation in CEX format.)  In-progress work on documents in XML can be incorporated into a CITE library for validation.

In addition, it requires that HC MID projects follow a set of conventions for organizing the contents in a predictable file system so that a script can assemble a CITE library from multiple source documents.



## Summary example

- [complete example](./walkthrough/): build a CITE library from files organized in MID conventions, and validate work on one page of a manuscript.

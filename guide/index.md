---
title: HCMID validator library
layout: page
---

**Library version @VERSION@**

## Generalized concepts for editorial project validation

The  `midvalidator` library defines abstract interfaces that can be implemented to validate editing for any of the HC MID Club's projects.  These include:

- [orthographies](./orthographies/).  Implementations of the `MidOrthography` trait define what characters are acceptable in a given orthographic system, and how the semantics of the characters' usage determine a classified tokenization of a text.
- [validators](./validators/).  Type-parameterized mplementations of the `MidValidator` trait apply some specific type of validation to elements of a CITE Library.  They include methods for validating an entire library, or for validating contents related to a specific text-bearing surface in the DSE model.  The results of their validation are also type parameterized.  Results of different types can be gathered in a `TestResultGroup`.

These abstractions are united in the `LibraryValidator` object.  Given a CITE library and a Vector of `MidValidator`s, the `LibraryValidator` can apply each validator successively, either to the entire library, or to material related to a single text-bearing surface.  


## Validating work in progress at HC MID

In order to use the `LibraryValidator` with work in progress, we first need to generate a CITE Library.  This requires two things.  First, a further generalized concept:

- [markup readers](./readers/).  Implementations of the `MidMarkupReader` trait define how citable texts formatted in a given system (perhaps using XML markup, or follwing defined conventions in a plain-text in a format like Markdown) can be interpreted in the OHCO2 model. One important use-case for these classes is to translate between one format implementing the OHCO2 model and another.  (For example, you could use an `MidMarkupReader` to read an XML text and write out a plain-text representation in CEX format.)  In-progress work on documents in XML can be incorporated into a CITE library for validation.

In addition, it requires that HC MID projects follow a set of conventions for organizing the contents in a predictable file system so that a script can assemble a CITE library from multiple source documents.

- [conventions for data organization](./directories/)


## Summary example

- overview of [building a CITE library from files organized in MID conventions, and validating work on one page of a manuscript](./walkthrough/).

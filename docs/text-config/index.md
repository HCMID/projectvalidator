---
title: Configuring text editions
layout: page
---

**Library version 13.1.0**

There are four required files in the `textConfig` directory.

Two of them provide the information necessary to create a corpus of citable texts in the OHCO2 model from the files in your `editions` repository:

- `catalog.cex`
- `citation.cex`

The other two define classes that can be used to generate tokenizable editions:

- `readers.cex`
- `orthographies.cex`

## Creating citable texts

### `catalog.cex`

Basic information needed to create a `CatalogEntry` in a CITE `TextRepository`:

    urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#language

#### `citation.cex`


Locates a document as a local file, and documents how its citation scheme works.

    Text URN#Format#Document#Namespace mapping#Citation model


### Creating tokenizable editions

## `readers.cex`

Associates a URN with an implementation of an `MidMarkupReader`.  URNs may be associated with more than one reader.  Since all projects must produce a diplomatic edition, the first entry for any URN must be a reader producing a diplomatic reading of the source document.

    text#MidReader class

## `orthographies.cex`

Associates a URN with an implementation of `MidOrthography`.  This makes it possible to tokenize a text.

    text#orthographic class

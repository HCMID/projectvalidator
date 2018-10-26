---
layout:  page
title: "The editorial repository"
---

An MID editorial repository musthave:


- a directory named `dse`.  DSE records are placed here.  File names must end in the extension `.cex`.
- a directory named `editions`, containing files named `catalog.cex` and `citation.cex`.  Editions are kept in this directory or subdirectories of it, as defined in `citation.cex`.
- a directory named `paleography`. Records of paleographic observations are placed here.  File names must end in the extension `.cex`.
- a directory named `header` containing `.cex` files defining the CEX `library` block and the definition of the DSE data model.  (This allows the validator to use the `scm` package's `CiteLibrary` class.)
- a writable directory named `validation` where markdown reports can be written

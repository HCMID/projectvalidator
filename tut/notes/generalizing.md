

## Top-level overview

The projectvalidator's main purpose is to validate a **CITE library**.

The `LibraryValidator` class should do this by:

1. taking a Vector of  `MidValidator` implementations;  these have in common a `validate` function that returns a type-parameterized `TestResults` for a given surface identified by `Cite2Urn`.
2. each `MidValidator` to a surface identified by `Cite2Urn`




### Type parameterization

The `MidValidator` trait defines a type-paramertized `validate` method that operates on a surface identified by `Cite2Urn`.  It returns a  `TestResults` object operating type-parameterized to the same type as the `validate` function is invoked with.

Bottom-up example of track of validation:


1.  `DseValidator` extends `MidValidator`.  Its `validate` function is specified as working on type `DsePassage` and returns a `TestResults` object of `DsePassage` type.
2.




### Building a CITE library from an MID project

Data/configuration we need for MID project validation:

MID project conventions for building content required to validate:

1.  create a `TextRepository`  from
    -   val catCex = "editions/ctscatalog.cex"
    -   val baseDir = "editions"
    -   val citeConf = "editions/citationconfig.cex"
2.  iipsrv base URL.  Instantiate a citebinaryimage `IIIFApi`
3.  class implementing `MidOrthography`. from "editions/orthographies.csv".
4.  class implementing `MidMarkupReader`. from "editions/readers.csv".
5.  dse triples.  Build a DSE vector from data in the `dse` folder.

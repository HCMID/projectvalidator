## Top-level overview

The `projectvalidator` library's main purpose is to validate a **CITE library**. It can be applied to a CITE library constructed in any fashion.

The `LibraryValidator` class should do this by:

1. taking a Vector of  `MidValidator` implementations;  these have in common a `validate` function that returns a Vector of type-parameterized `TestResult` for a given surface identified by `Cite2Urn`.



### Type parameterization


The `MidValidator` trait defines a type-parameterized `validate` method that operates on a surface identified by `Cite2Urn`.  It returns a type-parameterized `TestResult` of the same type as the `validate` function.

Bottom-up example of track of validation:


1.  `DseValidator` extends `MidValidator`.  Its `validate` function is specified as working on type `DsePassage` and returns a `TestResult` object of `DsePassage` type.




Tests:


- construct a `DseValidator` implementing `MidValidator[DsePassage]`and get its `validate` results.  This should be a Vector of `TestResult[DsePassage]`.
- pass a `DseValidator` in to a `LibraryValidator` and do the same thing.


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

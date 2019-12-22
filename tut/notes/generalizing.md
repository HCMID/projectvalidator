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

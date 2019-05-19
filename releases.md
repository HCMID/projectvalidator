## Release notes: MID   `projectvalidator` code library

**6.1.2**:  update library dependencies.

**6.1.1**:  support TEI `gap` element.


**6.1.0**:  add generalized `MidTeiReader` class.  This allows containing/terminal elements including TEI `p`, `ab`, `div`, `l`.  It therefore cannot, by itself, be used to ensure compliance with a citation scheme using any of these nodes for citation values.


**6.0.2**:  Harmonize versions of library dependencies.


**6.0.1**:  Remove evilplot dependency.

*6.0.0**:  Make function for CEX writing from CEX source concrete.

*5.7.0**: Changes dependency on library for CEX parsing, eliminating circular dependency with HMT text model library.

**5.6.0**: Add `citableNode` function to `MidToken`, and `tokenizedCorpus` function to `MidOrthography`.


**5.5.1**:  Correct bug in ScalaJS export.


**5.5.0**: Publish binaries correctly for scala 2.11 and 2.12.


**5.4.0**:  Add `TokenHistogram` class with histogram functions and charts with `evilplot`.


**5.3.0**:  Add `MidVerseLReader`.


**5.2.1**:  Fixes a bug in sorting of token histograms.

**5.2.0**: Add concrete `categoryHistogram` function to `MidOrthography` trait.

**5.1.0**: Add concrete `tokenizeCorpus` function to `MidOrthography` trait.

**5.0.0**: Further API-breaking changes in refactoring code.

**4.0.0**:  API-breaking changes introduced by more coherent naming of functions in `MidReader`.

**3.1.0**:  Support `seg` and `foreign` in `MidProseABReader`.

**3.0.1**:   Bug fix in binary publication process.

**3.0.0**:  API-breaking changes to `MidOrthography` trait and its implementation. Required tokenization now works on `CitableNode`s, not `String`s.

**2.0.0**: API-breaking changes to `EditorsRepo` with addition of new traits and classes for converting archival editions to publishable editions, and for working orthography of specific editions.  Build converted to cross-compile applicable contentto ScalaJS.

**1.3.0**: Adds implementations of `MidMarkupReader` trait.

**1.2.0**: Removes implementations of `MidOrthography` trait that have been incorporated in more appropriate libraries.

**1.1.0**: Adds `MidOrthography` and related traits and classes `MidTokenCategory`, and `MidToken`.

**1.0.1**:  improves user messages in validation reporting.

**1.0.0**:  validates paleographic observations and basic DSE relations.

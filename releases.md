## Release notes: MID   `projectvalidator` code library


**13.1.0**: builds library including all configured editions automatically generated.

**13.0.1**: updated `dse` library corrects off-by-one bug loading data.

**13.0.0**: API-crushing, radical refactoring with parts of 12.3.0 version broken out into `edu.holycross.shot.mid.markupreader`, `edu.holycross.shot.mid.orthography` and `edu.holycross.shot.citevalidator` libraries.

**12.3.0**: Adds validation by text-bearing surface to `LibraryValidator` object.

**12.2.2**: Correct summary text for display of `DsePassage` errors.

**12.2.1**: Republish 12.2.0 with sane logging level.

**12.2.0**: `DseValidator` now includes `verify` method.

**12.1.1**: Correct formatting of message

**12.1.0**:  Add `OrthographyValidator` and `TestResultGroup` classes.

**12.0.0**:  Change to `MidValidator` trait:  required `validate` function operates on a library, not a surface.

**11.0.0**:  Change to `LibraryValidator` API: now a static object rather than case class.

**10.0.0**:  API redesign making `MidValidator` a trait.


**9.2.0**:  Adds logging support and updates library dependencies.


**9.1.0**:  Adds `size` function on `TokenizableCorpus`.

**9.0.1**: Removes erroneously included circular dependency.

**9.0.0**:  Implementations of the `MidMarkupReader` trait are broken out into their own package.  See https://github.com/hcmid/markupreaders.

**8.0.0**: `concordance` function of `TokenizableCorpus`  now creates a `Map`, not a Vector.

**7.2.1**:  Tokenization now lower cases tokens before counting.

**7.2.0**:  Adds the `TokenizableCorpus` class.


**7.1.0**:  Adds the `MidVerseLReaderDiplomatic` class.

**7.0.0**:  API-breaking, ground-up rewrite.  Will require more extensive documentation than release notes to document how it makes use of the `MidOrthography` and `MidMarkupReader` abstractions, and now cleanly separates such distinct issues as organization of files in an editorial repository, assessment of the validity of a CITE Library, and how to summarize and report on automated testing.

**6.7.0**: require readers and orthographies configurations in editorial repositories

**6.6.0**:  allow optional configure of non-standard names for text repository components.

**6.5.0**: substantial performance improvements with updated `dse` library.

**6.4.0**: updated `dse` library with some performance improvement.

**6.3.0**:  add `DseReporter` function to format Markdown view of a passage identified by `CtsUrn`.

**6.2.5**: use `Corpus` to sort more reliably.

**6.2.4**: sort better.

**6.2.3**: sort passages by document order in `passageView` reporting


**6.2.2**:  better handling of cases where no texts in the corpus match for a surface with indexed texts.

**6.2.1**: correct passage view display to work by requested surface only.  Better reporting in markdown output.

**6.2.0**: update `dse` library, add markdown output to view report with image and transcribed text interleaved.

**6.1.3**:  update `scm` library.

**6.1.2**:  update library dependencies.

**6.1.1**:  support TEI `gap` element.


**6.1.0**:  add generalized `MidTeiReader` class.  This allows containing/terminal elements including TEI `p`, `ab`, `div`, `l`.  It therefore cannot, by itself, be used to ensure compliance with a citation scheme using any of these nodes for citation values.


**6.0.2**:  Harmonize versions of library dependencies.


**6.0.1**:  Remove evilplot dependency.

**6.0.0**:  Make function for CEX writing from CEX source concrete.

**5.7.0**: Changes dependency on library for CEX parsing, eliminating circular dependency with HMT text model library.

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

## Release notes: MID   `projectvalidator` code library


*5.1.0**: Add concrete `tokenizeCorpus` function to `MidOrthography` trait.

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

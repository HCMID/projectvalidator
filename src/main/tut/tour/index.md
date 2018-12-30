---
layout: page
title: A hands-on tour of the library
---

Obviously, import the library.

```tut:silent
import edu.holycross.shot.mid.validator._
```



## The editorial repository
Everythng starts with an `EditorsRepo`.  You can create one from a relative or absolute path (as a String) to the root of the repository.

```tut:silent
val repo = EditorsRepo("src/test/resources/iliad10")
```

This class enforces MID conventions for organizing data in an editorial project, and will throw an Exception if your repository is missing any required content.




-  The `Validator` class validates the contents of an `EditorsRepo`.
-  A `Validator` object can construct a `DseVector` and validate DSE relations.


Implemented by not fully integrated:

-  Implementations of the `MidEditionType` trait define kinds of editions that can be generated from this repository.
-  Classes implementing the `MidMarkupReader` trait provide validation of textual content.  `MidMarkupReader` instances are created for specific `MidEditionType`s.


Orthography:

- An orthography can tokenize strings into a sequence of `MidToken`s.
- `MidToken` objects classify strings as belonging to `MidTokenTypes`.


[Nifty utilities](utils)

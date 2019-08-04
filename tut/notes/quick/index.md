---
layout: page
title:  Quick start
---


Import the library:


```tut:silent
import edu.holycross.shot.virgapes._
```



## `Neume`s

The basic object in the library is a `Neume`.  You can create it from a `String` in virgapes notation, and then work with the neume's components.

Example:  the string `1.2.0.0` represents a one-syllable neume with ID 2, no episema (ie, episema on syllable 0), and no liquescence.


```tut:silent
val neume = Neume("1.2.0.0")
assert(neume.name == "punctum")
assert(neume.pitches == 1)
assert(neume.episema == 0)
assert(neume.liquescence == false)
```

You can also retrieve a `NeumeType`  option for a `Neume`.

```tut:silent
val nType = neume.neumeType
assert(nType.get == Punctum)
```

See the [API docs](https://neelsmith.github.io/virgapes/api/edu/holycross/shot/virgapes/index.html) for  more information.

## `Syllable`s

A `Syllable` has a vector of `Neume`s.  You can create a Vector of `Syllable`s from a `String`.  Syllables are separated by white space;  within a single syllable, neumes are separated by hyphens.

Example:  the string `1.2.0.0 1.2.0.0 0.12.0.0-1.3.0.0` represents three syllables.  The first and second syllable each have one neume;  the final syllable has two neumes.

```tut:silent
val sylls =  Syllable("1.2.0.0 1.2.0.0 0.12.0.0-1.3.0.0")
assert(sylls.size == 3)

val s1 = sylls(0)
assert (s1.size == 1)
val n1 = s1.neumes(0)
assert(n1.neumeType.get == Punctum)
assert(n1.episema == 0)
assert(n1.liquescence == false)
```


## Citable texts


You can create a vector of `Syllable`s from a `CitableNode`, an implementation of the OHCO2 text model in the `ohco2` library.  You'll need to import both the `ohco2` and `cite` libraries.

```tut:silent
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
```

The following example uses the same data as the previous example, but draws it from a `CitableNode`.

```tut:silent
val u = CtsUrn("urn:cts:chant:antiphonary.eins121.mid:nativity.3")
val cn = CitableNode(u,"1.2.0.0 1.2.0.0 0.12.0.0-1.3.0.0")
val citedSylls = Syllable(cn)

val cited1 = citedSylls(0)
assert (cited1.size == 1)
val citedNeume1 = cited1.neumes(0)
assert(citedNeume1.neumeType.get == Punctum)
assert(citedNeume1.episema == 0)
assert(citedNeume1.liquescence == false)
```

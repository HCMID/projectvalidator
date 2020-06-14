---
title: Walk through an example
layout: page
---

**Library version 13.0.0**


## 1. Build a CITE library

Import the libraries we'll use:


```scala
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.mid.markupreader._
```

In this example, we'll use a single markup reader, and create an `EditorsRepo`
from the directory `chantsample`.




```scala
val readerMap =   Map(
  "DiplomaticReader" -> Vector(MidProseABDiplomatic)
)
// readerMap: Map[String, Vector[MidProseABDiplomatic.type]] = Map(
//   "DiplomaticReader" -> Vector(
//     edu.holycross.shot.mid.markupreader.MidProseABDiplomatic$@160aef4b
//   )
// )
val repo = EditorsRepo("jvm/src/test/resources/chantsample", readerMap)
// repo: EditorsRepo = EditorsRepo(
//   "jvm/src/test/resources/chantsample",
//   Map(
//     "DiplomaticReader" -> Vector(
//       edu.holycross.shot.mid.markupreader.MidProseABDiplomatic$@160aef4b
//     )
//   )
// )
```

The `EditorsRepo` assembles the CITE library we need:


```scala
val lib = repo.library
```


## 2. Validate a page in the library

For this example, we'll apply a single validator:


```scala
val dseValidator = DseValidator(lib)
val validators = Vector(dseValidator)
```

Now we can use the CITE `LibraryValidator` object to validate a page in the library using the given list of validators.

Create a URN for the page to validate:

```scala
import edu.holycross.shot.cite._
val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
```

```scala
val rslts = LibraryValidator.validate(pg, validators)
// rslts: Vector[TestResult[Any]] = Vector(
//   TestResult(
//     true,
//     "- Compare text urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1 to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/ecod/einsiedeln121imgs/v1/sbe_0121_021.tif/pct:42.24,67.53,29.02,6.05/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.4224,0.6753,0.2902,0.06050)Text passage urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1 found in corpus. ",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_0"),
//       "Passage 0",
//       CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1"),
//       Cite2Urn(
//         "urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.4224,0.6753,0.2902,0.06050"
//       ),
//       Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
//     )
//   ),
//   TestResult(
//     false,
//     "- Compare text urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/ecod/einsiedeln121imgs/v1/sbe_0121_021.tif/pct:5.747,65.69,67.69,20.52/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.05747,0.6569,0.6769,0.2052)Indexed passage urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in **NOT FOUND** in text corpus.",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_1"),
//       "Passage 1",
//       CtsUrn(
//         "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in"
//       ),
//       Cite2Urn(
//         "urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.05747,0.6569,0.6769,0.2052"
//       ),
//       Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
//     )
//   )
// )
```

The resulting Vector of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.

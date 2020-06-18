---
layout: page
title: Markup readers
---

**Library version 13.0.1**


> Add a complete MID validation script here!

A script to validate MID work in progress should do these things.



## Import required libraries

You always need these:

```scala
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.mid.markupreader._
import edu.holycross.shot.cite._
```

## Define markup readers

Define the markup reader classes to apply for a given key in `textConfig/readers.cex`:

```scala
def readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "MidProseAB" ->   Vector(MidProseABDiplomatic)
)
```

## Create an `EditorsRepo`

Give it the path to the root directory, and the map of markup readers you created..  (You might just use `"."` and require the script to be run from the repository root.)

```scala
val repo = EditorsRepo(repoRoot, readerMap)
```

## Define appropriate validators for your project

*All* MID projects require DSE validation.  You can add as many further validators as are appropriate for the particular content of your project to the Vector of validators.


```scala
val dseValidator = DseValidator(repo.library)
val validators = Vector(dseValidator)
```


## Define a simple validation function

You'll
```scala
def validate(pageUrn: String) = {
  val pg = Cite2Urn(pageUrn)  
  LibraryValidator.validate(pg, validators)
}
```

```scala
validate("urn:cite2:ecod:eins121pages.v1:21")
// res0: Vector[TestResult[Any]] = Vector(
//   TestResult(
//     true,
//     "Compare text urn:cts:chant:massordinary.eins121.text:h007_2.h00.1 to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/ecod/einsiedeln121imgs/v1/sbe_0121_021.tif/pct:42.24,67.53,29.02,6.05/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.4224,0.6753,0.2902,0.06050)Text passage urn:cts:chant:massordinary.eins121.text:h007_2.h00.1 found in corpus. ",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_0"),
//       "Passage 0",
//       CtsUrn("urn:cts:chant:massordinary.eins121.text:h007_2.h00.1"),
//       Cite2Urn(
//         "urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.4224,0.6753,0.2902,0.06050"
//       ),
//       Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
//     )
//   ),
//   TestResult(
//     false,
//     "Compare text urn:cts:chant:massordinary.eins121.text:h007_2.h02.1@Mem-h02.1@in to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/ecod/einsiedeln121imgs/v1/sbe_0121_021.tif/pct:5.747,65.69,67.69,20.52/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.05747,0.6569,0.6769,0.2052)Indexed passage urn:cts:chant:massordinary.eins121.text:h007_2.h02.1@Mem-h02.1@in **NOT FOUND** in text corpus.",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_1"),
//       "Passage 1",
//       CtsUrn(
//         "urn:cts:chant:massordinary.eins121.text:h007_2.h02.1@Mem-h02.1@in"
//       ),
//       Cite2Urn(
//         "urn:cite2:ecod:einsiedeln121imgs.v1:sbe_0121_021@0.05747,0.6569,0.6769,0.2052"
//       ),
//       Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
//     )
//   )
// )
```

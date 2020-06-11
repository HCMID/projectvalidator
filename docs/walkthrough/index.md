---
title: Walk through an example
layout: page
---

**Library version 12.2.2**


Import all libraries we'll use:

```scala
// Generic libraries we always use:
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._

// For project-specific markup reader:
import org.homermultitext.edmodel._
```

In this example, we'll use a single markup reader, and create an `EditorsRepo`
from the directory `hmtexample`:

```scala
val readerMap =   Map(
  "DiplomaticReader" ->   Vector(DiplomaticReader)
)
// readerMap: Map[String, Vector[DiplomaticReader.type]] = Map(
//   "DiplomaticReader" -> Vector(org.homermultitext.edmodel.DiplomaticReader$@16b1fd0f)
// )

val repo = EditorsRepo("hmtexample",
readerMap)
// repo: EditorsRepo = EditorsRepo(
//   "hmtexample",
//   Map(
//     "DiplomaticReader" -> Vector(org.homermultitext.edmodel.DiplomaticReader$@16b1fd0f)
//   )
// )
```

The `EditorsRepo` assembles the CITE library we need:

```scala
val lib = repo.library
// lib: edu.holycross.shot.scm.CiteLibrary = CiteLibrary(
//   "MID manuscriptshackathon 2019",
//   Cite2Urn("urn:cite2:hcmid:mshackathon.cex.2019workshop:all"),
//   "Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.",
//   Vector(),
//   Some(
//     TextRepository(
//       Corpus(
//         Vector(
//           CitableNode(
//             CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.1"),
//             "<l n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u1f61\u03c2 \u03bf\u1f31 \u03bc\u1f72\u03bd \u03c3\u03c4\u03b5\u03bd\u03ac\u03c7\u03bf\u03bd\u03c4\u03bf \u03ba\u03b1\u03c4\u03b1 \u03c0\u03c4\u03cc\u03bb\u03b9\u03bd~ \u03b1\u1f50\u03c4\u1f70\u03c1 <rs n=\"urn:cite2:hmt:place.v1:place96\" type=\"ethnic\">\u1f08\u03c7\u03b1\u03b9\u03bf\u1f76</rs>.</l>"
//           ),
//           CitableNode(
//             CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.2"),
//             "<l n=\"2\" xmlns=\"http://www.tei-c.org/ns/1.0\"> \u1f10\u03c0\u03b5\u03b9\u03b4\u1f74 \u03bd\u1fc6\u03ac\u03c2 \u03c4\u03b5 \u03ba\u03b1\u1f76 <placeName n=\"urn:cite2:hmt:place.v1:place22\">\u1f19\u03bb\u03bb\u03ae\u03c3\u03c0\u03bf\u03bd\u03c4\u03bf\u03bd</placeName> \u1ffe\u0390\u03ba\u03bf\u03bd\u03c4\u03bf.</l>"
//           ),
//           CitableNode(
//             CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.3"),
//             "<l n=\"3\" xmlns=\"http://www.tei-c.org/ns/1.0\"> \u03bf\u1f31 \u03bc\u1f72\u03bd \u1f70\u03c1 \u1f10\u03c3\u03ba\u03af\u03b4\u03bd\u03b1\u03bd\u03c4\u03bf \u1f11\u1f74\u03bd \u1f10\u03c0\u03b9 \u03bd\u1fc6\u03b1 \u1f15\u03ba\u03b1\u03c3\u03c4\u03bf\u03c2~</l>"
//           ),
//           CitableNode(
//             CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.4"),
//             "<l n=\"4\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u039c\u03c5\u03c1\u03bc\u03b9\u03b4\u03cc\u03bd\u03b1\u03c2 \u03b4\u2019 \u03bf\u1f50\u03ba \u03b5\u1f34\u03b1 \u1f00\u03c0\u03bf\u03c3\u03ba\u03af\u03b4\u03bd\u03b1\u03c3\u03b8\u03b1\u03b9 <persName n=\"urn:cite2:hmt:pers.v1:pers1\">\u1f08\u03c7\u03b9\u03bb\u03bb\u03b5\u03cd\u03c2</persName>\u0387</l>"
//           ),
//           CitableNode(
//             CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.5"),
//             "<l n=\"5\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u1f00\u03bb\u03bb' \u1f45 \u03b3\u03b5, \u03bf\u1f37\u03c2, \u1f11\u03c4\u03ac\u03c1\u03bf\u03b9\u03c3\u03b9 \u03c6\u03b9\u03bb\u03bf\u03c0\u03c4\u03bf\u03bb\u03ad\u03bc\u03bf\u03b9\u03c3\u03b9 \u03bc\u03b5\u03c4\u03b7\u03cd\u03b4\u03b1~</l>"
//           ),
//           CitableNode(
// ...
```

For this example, we'll create a Vector with a single validator:

```scala
val dseValidator = DseValidator(lib)
// dseValidator: DseValidator = DseValidator(
//   CiteLibrary(
//     "MID manuscriptshackathon 2019",
//     Cite2Urn("urn:cite2:hcmid:mshackathon.cex.2019workshop:all"),
//     "Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.",
//     Vector(),
//     Some(
//       TextRepository(
//         Corpus(
//           Vector(
//             CitableNode(
//               CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.1"),
//               "<l n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u1f61\u03c2 \u03bf\u1f31 \u03bc\u1f72\u03bd \u03c3\u03c4\u03b5\u03bd\u03ac\u03c7\u03bf\u03bd\u03c4\u03bf \u03ba\u03b1\u03c4\u03b1 \u03c0\u03c4\u03cc\u03bb\u03b9\u03bd~ \u03b1\u1f50\u03c4\u1f70\u03c1 <rs n=\"urn:cite2:hmt:place.v1:place96\" type=\"ethnic\">\u1f08\u03c7\u03b1\u03b9\u03bf\u1f76</rs>.</l>"
//             ),
//             CitableNode(
//               CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.2"),
//               "<l n=\"2\" xmlns=\"http://www.tei-c.org/ns/1.0\"> \u1f10\u03c0\u03b5\u03b9\u03b4\u1f74 \u03bd\u1fc6\u03ac\u03c2 \u03c4\u03b5 \u03ba\u03b1\u1f76 <placeName n=\"urn:cite2:hmt:place.v1:place22\">\u1f19\u03bb\u03bb\u03ae\u03c3\u03c0\u03bf\u03bd\u03c4\u03bf\u03bd</placeName> \u1ffe\u0390\u03ba\u03bf\u03bd\u03c4\u03bf.</l>"
//             ),
//             CitableNode(
//               CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.3"),
//               "<l n=\"3\" xmlns=\"http://www.tei-c.org/ns/1.0\"> \u03bf\u1f31 \u03bc\u1f72\u03bd \u1f70\u03c1 \u1f10\u03c3\u03ba\u03af\u03b4\u03bd\u03b1\u03bd\u03c4\u03bf \u1f11\u1f74\u03bd \u1f10\u03c0\u03b9 \u03bd\u1fc6\u03b1 \u1f15\u03ba\u03b1\u03c3\u03c4\u03bf\u03c2~</l>"
//             ),
//             CitableNode(
//               CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.4"),
//               "<l n=\"4\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u039c\u03c5\u03c1\u03bc\u03b9\u03b4\u03cc\u03bd\u03b1\u03c2 \u03b4\u2019 \u03bf\u1f50\u03ba \u03b5\u1f34\u03b1 \u1f00\u03c0\u03bf\u03c3\u03ba\u03af\u03b4\u03bd\u03b1\u03c3\u03b8\u03b1\u03b9 <persName n=\"urn:cite2:hmt:pers.v1:pers1\">\u1f08\u03c7\u03b9\u03bb\u03bb\u03b5\u03cd\u03c2</persName>\u0387</l>"
//             ),
//             CitableNode(
//               CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.5"),
//               "<l n=\"5\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u1f00\u03bb\u03bb' \u1f45 \u03b3\u03b5, \u03bf\u1f37\u03c2, \u1f11\u03c4\u03ac\u03c1\u03bf\u03b9\u03c3\u03b9 \u03c6\u03b9\u03bb\u03bf\u03c0\u03c4\u03bf\u03bb\u03ad\u03bc\u03bf\u03b9\u03c3\u03b9 \u03bc\u03b5\u03c4\u03b7\u03cd\u03b4\u03b1~</l>"
//             ),
// ...
val validators = Vector(dseValidator)
// validators: Vector[DseValidator] = Vector(
//   DseValidator(
//     CiteLibrary(
//       "MID manuscriptshackathon 2019",
//       Cite2Urn("urn:cite2:hcmid:mshackathon.cex.2019workshop:all"),
//       "Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.",
//       Vector(),
//       Some(
//         TextRepository(
//           Corpus(
//             Vector(
//               CitableNode(
//                 CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.1"),
//                 "<l n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u1f61\u03c2 \u03bf\u1f31 \u03bc\u1f72\u03bd \u03c3\u03c4\u03b5\u03bd\u03ac\u03c7\u03bf\u03bd\u03c4\u03bf \u03ba\u03b1\u03c4\u03b1 \u03c0\u03c4\u03cc\u03bb\u03b9\u03bd~ \u03b1\u1f50\u03c4\u1f70\u03c1 <rs n=\"urn:cite2:hmt:place.v1:place96\" type=\"ethnic\">\u1f08\u03c7\u03b1\u03b9\u03bf\u1f76</rs>.</l>"
//               ),
//               CitableNode(
//                 CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.2"),
//                 "<l n=\"2\" xmlns=\"http://www.tei-c.org/ns/1.0\"> \u1f10\u03c0\u03b5\u03b9\u03b4\u1f74 \u03bd\u1fc6\u03ac\u03c2 \u03c4\u03b5 \u03ba\u03b1\u1f76 <placeName n=\"urn:cite2:hmt:place.v1:place22\">\u1f19\u03bb\u03bb\u03ae\u03c3\u03c0\u03bf\u03bd\u03c4\u03bf\u03bd</placeName> \u1ffe\u0390\u03ba\u03bf\u03bd\u03c4\u03bf.</l>"
//               ),
//               CitableNode(
//                 CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.3"),
//                 "<l n=\"3\" xmlns=\"http://www.tei-c.org/ns/1.0\"> \u03bf\u1f31 \u03bc\u1f72\u03bd \u1f70\u03c1 \u1f10\u03c3\u03ba\u03af\u03b4\u03bd\u03b1\u03bd\u03c4\u03bf \u1f11\u1f74\u03bd \u1f10\u03c0\u03b9 \u03bd\u1fc6\u03b1 \u1f15\u03ba\u03b1\u03c3\u03c4\u03bf\u03c2~</l>"
//               ),
//               CitableNode(
//                 CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.4"),
//                 "<l n=\"4\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u039c\u03c5\u03c1\u03bc\u03b9\u03b4\u03cc\u03bd\u03b1\u03c2 \u03b4\u2019 \u03bf\u1f50\u03ba \u03b5\u1f34\u03b1 \u1f00\u03c0\u03bf\u03c3\u03ba\u03af\u03b4\u03bd\u03b1\u03c3\u03b8\u03b1\u03b9 <persName n=\"urn:cite2:hmt:pers.v1:pers1\">\u1f08\u03c7\u03b9\u03bb\u03bb\u03b5\u03cd\u03c2</persName>\u0387</l>"
//               ),
//               CitableNode(
//                 CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.5"),
//                 "<l n=\"5\" xmlns=\"http://www.tei-c.org/ns/1.0\">\u1f00\u03bb\u03bb' \u1f45 \u03b3\u03b5, \u03bf\u1f37\u03c2, \u1f11\u03c4\u03ac\u03c1\u03bf\u03b9\u03c3\u03b9 \u03c6\u03b9\u03bb\u03bf\u03c0\u03c4\u03bf\u03bb\u03ad\u03bc\u03bf\u03b9\u03c3\u03b9 \u03bc\u03b5\u03c4\u03b7\u03cd\u03b4\u03b1~</l>"
// ...
```

Now we can use the `LibraryValidator` to validate a page in the library using the given list of validators:

```scala
val pg = Cite2Urn("urn:cite2:hmt:msA.v1:292v")
// pg: Cite2Urn = Cite2Urn("urn:cite2:hmt:msA.v1:292v")
val rslts = LibraryValidator.validate(lib,validators,pg)
// rslts: Vector[TestResult[Any]] = Vector(
//   TestResult(
//     true,
//     "- Compare text urn:cts:greekLit:tlg0012.tlg001.msA:23.2 to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA292VN_0794.tif/pct:49.2,53.34,35.09999,2.4/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA292VN_0794@0.492,0.5334,0.351,0.024)Text passage urn:cts:greekLit:tlg0012.tlg001.msA:23.2 found in corpus. ",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_0"),
//       "Passage 0",
//       CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.2"),
//       Cite2Urn(
//         "urn:cite2:hmt:vaimg.2017a:VA292VN_0794@0.492,0.5334,0.351,0.024"
//       ),
//       Cite2Urn("urn:cite2:hmt:msA.v1:292v")
//     )
//   ),
//   TestResult(
//     true,
//     "- Compare text urn:cts:greekLit:tlg0012.tlg001.msA:23.10 to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA292VN_0794.tif/pct:47.59999,68.6,37.4,2.4/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA292VN_0794@0.476,0.686,0.374,0.024)Text passage urn:cts:greekLit:tlg0012.tlg001.msA:23.10 found in corpus. ",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_8"),
//       "Passage 8",
//       CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.10"),
//       Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA292VN_0794@0.476,0.686,0.374,0.024"),
//       Cite2Urn("urn:cite2:hmt:msA.v1:292v")
//     )
//   ),
//   TestResult(
//     true,
//     "- Compare text urn:cts:greekLit:tlg0012.tlg001.msA:23.3 to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA292VN_0794.tif/pct:48.9,55.22,35.79999,2.4/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA292VN_0794@0.489,0.5522,0.358,0.024)Text passage urn:cts:greekLit:tlg0012.tlg001.msA:23.3 found in corpus. ",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_1"),
//       "Passage 1",
//       CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:23.3"),
//       Cite2Urn(
// ...
```

The resulting Vetor of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.

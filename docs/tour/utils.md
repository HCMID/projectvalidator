---
layout:  page
title: "Nifty utilities"
---



The package object includes a function called `collectText` for recursively collecting text from a parsed XML node.  To illustrate it, we'll use Scala's built-in XML parser to parse a String, and compare the .


```scala
scala> import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.validator._

scala> import scala.xml._
import scala.xml._

scala> val xml = XML.loadString("<root>Level 1 <div>contained level 2 div,</div><div> another level two div, <sub>which contained a third tier,</sub> and back to second level.</div></root>")
xml: scala.xml.Elem = <root>Level 1 <div>contained level 2 div,</div><div> another level two div, <sub>which contained a third tier,</sub> and back to second level.</div></root>

scala> val extracted = collectText(xml).trim
extracted: String = Level 1 contained level 2 div, another level two div, which contained a third tier, and back to second level.
```

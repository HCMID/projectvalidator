---
layout:  page
title: "Nifty utilities"
---



The package object includes a function called `collectText` for recursively collecting text from a parsed XML node.  To illustrate it, we'll use Scala's built-in XML parser to parse a String, and compare the .


```tut
import edu.holycross.shot.mid.validator._
import scala.xml._

val xml = XML.loadString("<root>Level 1 <div>contained level 2 div,</div><div> another level two div, <sub>which contained a third tier,</sub> and back to second level.</div></root>")
val extracted = collectText(xml).trim
```

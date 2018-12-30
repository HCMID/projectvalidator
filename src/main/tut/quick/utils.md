---
layout:  page
title: "Nifty utilities"
---



The package object includes a function for recursively collecting text from a parsed XML node.  To test it, we'll use Scala's built-in XML parser.


```tut
import edu.holycross.shot.mid.validator._
import scala.xml._

val xml = XML.loadString("<root>Level 1 <div>contained 2</div><div>, more two <sub>third tier</sub> and back to two</div></root>")
val actual = collectText(xml).trim
val expected = "Level 1 contained 2 , more two third tier and back to two"
assert(actual == expected)
```

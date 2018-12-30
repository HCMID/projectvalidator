---
layout:  page
title: "Nifty utilities"
---



The package object includes a function for recursively collecting text from a parsed XML node.  To test it, we'll use Scala's built-in XML parser.


```scala
scala> import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.validator._

scala> import scala.xml._
import scala.xml._

scala> val xml = XML.loadString("<root>Level 1 <div>contained 2</div><div>, more two <sub>third tier</sub> and back to two</div></root>")
xml: scala.xml.Elem = <root>Level 1 <div>contained 2</div><div>, more two <sub>third tier</sub> and back to two</div></root>

scala> val actual = collectText(xml).trim
actual: String = Level 1 contained 2 , more two third tier and back to two

scala> val expected = "Level 1 contained 2 , more two third tier and back to two"
expected: String = Level 1 contained 2 , more two third tier and back to two

scala> assert(actual == expected)
```

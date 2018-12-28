package edu.holycross.shot.mid

/** Provides classes for validating editorial work on the Homer Multitext
project.
* See https://homermultext.github.io/ for documentation.
*
* ==Overview==
* The class  [[Validator]] manages contents of an [[EditorsRepo]].  It
* can be constructucted from a string path for the root of the
* repository.  For example:
*
* {{{
* val mom = Validator("REPOROOT")
* }}}
*
*
*/
package object validator {


  /** Awesome regex to select text while keeping
  * delimiting string in the resulting match group.
  */
  val  includeDelimiterRE = "((?<=%1$s)|(?=%1$s))"


  /** Base URL for Image Citation Tools.*/
  val ictBase= "http://www.homermultitext.org/ict2/"

  /** Icon image for good results.*/
  val okImg = "http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA311RN_0481.tif&RGN=0.6043,0.2275,0.01013,0.008714&WID=50&CVT=JPEG"

  val sadImg = "http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/vbimg/2017a/VB216VN_0316.tif&RGN=0.4788,0.7559,0.01419,0.007746&WID=50&CVT=JPEG"


  /** Recursively collect all text contained in
  * a branches of a given XML node.
  *
  * @param n Root of subtree to collect text from.
  * @param s Previously seen string data to add new
  * contents to.
  */
  def collectText(n: xml.Node, s: String = ""): String = {
    val txt = StringBuilder.newBuilder
    n match {
      case t: xml.Text =>  {
        val cleaner = t.toString().trim
        if (cleaner.nonEmpty){
          txt.append(cleaner + " ")
        }

      }
      case e: xml.Elem =>  {

        for (ch <- e.child) {
         txt.append(collectText(ch, s))
       }
     }
    }
    txt.toString
  }

}

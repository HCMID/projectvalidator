package edu.holycross.shot.mid

import better.files._
import File._
import java.io.{File => JFile}
import better.files.Dsl._



/** Provides classes for validating editorial work following HC MID conventions.
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


  def nameBetterFile(dir: File, fName: String): File = {
    dir/fName
  }

}

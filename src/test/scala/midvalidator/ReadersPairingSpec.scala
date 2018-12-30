package edu.holycross.shot.mid.validator
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._


class ReadersPairingSpec extends FlatSpec {

  "A ReadersPairing" should "have a vector of readers" in {
    val eins121 = CtsUrn("urn:cts:chant:massordinary.eins121:")
    val readers = MidProseABReader.readers

    val pairings = ReadersPairing(eins121, readers)

    assert (pairings.readers == readers)

  }
}

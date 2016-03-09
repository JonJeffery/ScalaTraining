package com.typesafe.training.scalatrain
package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.{ Matchers, WordSpec }

class HopSpec extends WordSpec with Matchers {

  "Creating a Hop" should {
    "throw an IllegalArgumentException for a station that is not visited by the train" in {
      an[IAE] should be thrownBy Hop(munich, essen, ice724)
    }

    "departureTime" should {
      "be initialized correctly" in {
        hop.departureTime shouldEqual ice724MunichTime
      }
    }

    "arrivalTime" should {
      "be initialized correctly" in {
        hop.arrivalTime shouldEqual ice724CologneTime
      }
    }
  }
}

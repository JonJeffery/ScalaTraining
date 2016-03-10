/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.{ Matchers, WordSpec }
import scala.collection.immutable.Seq

class TrainSpec extends WordSpec with Matchers {

  "Train ice724" should {
    "stop in Nurember" in {
      ice724.timeAt(nuremberg) shouldEqual Some(ice724NurembergTime)
    }
    "not stop in Essen" in {
      ice724.timeAt(essen) shouldEqual None
    }
  }

  "Train ice726" should {
    "stop in Munich" in {
      ice726.timeAt(munich) shouldEqual Some(ice726MunichTime)
    }
    "not stop in Cologne" in {
      ice726.timeAt(cologne) shouldEqual None
    }
  }

  "Creating a Train" should {
    "throw an IllegalArgumentException for a schedule with 0 or 1 elements" in {
      an[IAE] should be thrownBy Train(InterCityExpress(724), Schedule(Seq(
        (Seq(), Set(DayOfWeek("WED").get))
      )))

      an[IAE] should be thrownBy Train(InterCityExpress(724), Schedule(Seq(
        (Seq(ice724MunichItem), Set(DayOfWeek("WED").get))
      )))
    }
  }

  "stations" should {
    "be initialized correctly" in {
      ice724.stations shouldEqual Vector(munich, nuremberg, frankfurt, cologne)
    }
  }

  "backToBack" should {
    "be initialized correctly" in {
      ice724.backToBack shouldEqual Seq((munich, nuremberg), (nuremberg, frankfurt), (frankfurt, cologne))
    }
  }

  "departureTimes" should {
    "be initialized correctly" in {
      ice726.departureTimes shouldEqual Vector(
        munich -> ice726MunichTime,
        nuremberg -> ice726NurembergTime,
        frankfurt -> ice726FrankfurtTime,
        essen -> ice726CologneTime
      )

    }
  }
}

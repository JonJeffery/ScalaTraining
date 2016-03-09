/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain
package com.typesafe.training.scalatrain

import TestData._
import org.scalatest.{Matchers, WordSpec}

class JourneyPlannerSpec extends WordSpec with Matchers {

  "stations" should {
    "be initialized correctly" in {
      planner.stations shouldEqual Set(munich, nuremberg, frankfurt, cologne, essen)
    }
  }

  "hopMap" should {
    "be initialized correctly" in {
      planner.hopMap shouldEqual Map(
        munich -> Set(Hop(munich, nuremberg, ice724), Hop(munich, nuremberg, ice726)),
        nuremberg -> Set(Hop(nuremberg, frankfurt, ice724), Hop(nuremberg, frankfurt, ice726)),
        frankfurt -> Set(Hop(frankfurt, cologne, ice724), Hop(frankfurt, essen, ice726))
      )
    }
  }

  "Calling trainsAt" should {
    "return the correct trains" in {
      planner.trainsAt(munich) shouldEqual Set(ice724, ice726)
      planner.trainsAt(cologne) shouldEqual Set(ice724)
    }
  }

  "Calling stopsAt" should {
    "return the correct stops" in {
      planner.stopsAt(munich) shouldEqual Set(ice724MunichTime -> ice724, ice726MunichTime -> ice726)
    }
  }

  "Calling isShortTrip" should {
    "return false for more than one station in between" in {
      planner.isShortTrip(munich, cologne) shouldBe false
      planner.isShortTrip(munich, essen) shouldBe false
    }
    "return true for zero or one stations in between" in {
      planner.isShortTrip(munich, nuremberg) shouldBe true
      planner.isShortTrip(munich, frankfurt) shouldBe true
      planner.isShortTrip(nuremberg, frankfurt) shouldBe true
      planner.isShortTrip(nuremberg, essen) shouldBe true
    }
  }

  "Calling calculateConnections" should {
    "return a Set of Seq of Hops with 1 hop: M to N on 724" in {
      planner.calculatePathsBetweenStations(munich, nuremberg, ice724MunichTime) shouldEqual Set(Seq(hop_M_N_724))
    }
  }

  "Calling calculateConnections" should {
    "return a Set of Seq of Hops with 2 hops M to N to F on 724" in {
      planner.calculatePathsBetweenStations(munich, frankfurt, ice724MunichTime) shouldEqual Set(Seq(hop_M_N_724, hop_N_F_724))
    }
  }

  "Calling calculateConnections" should {
    "return a Set of Seq of Hops with 3 hops M to N to F to C on 724" in {
      planner.calculatePathsBetweenStations(munich, cologne, ice724MunichTime) shouldEqual Set(Seq(hop_M_N_724 , hop_N_F_724, hop_F_C_724))
    }
  }

  //sortPathsByTotalTime

  "Calling sortPathsByTotalTime" should {
    "return a Seq of Seq of Hops with 2 hops M to N to F sorted properly" in {
      val threePaths = planner.calculatePathsBetweenStations(munich, frankfurt, ice726MunichTime)
      val path1 = Seq(hop_M_N_724, hop_N_F_724)
      val path2 = Seq(hop_M_N_726, hop_N_F_726)
      val path3 = Seq(hop_M_N_726, hop_N_F_724)
      planner.sortPathsByTotalTime(threePaths) shouldEqual Seq(path2, path1, path3)
    }
  }
}

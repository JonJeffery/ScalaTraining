/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.{ Matchers, WordSpec }
import scala.collection.immutable.Seq

class TicketSpec extends WordSpec with Matchers {

  "fare" should {
    "be initialized in discountTicket1 correctly" in {
      discountTicket1.fare shouldEqual trip1.cost * .75
    }
    "be initialized in discountTicket2 correctly" in {
      discountTicket2.fare shouldEqual trip2.cost * .75
    }
    "be initialized in samePriceTicket correctly" in {
      samePriceTicket.fare shouldEqual trip1.cost
    }
    "be initialized in highPriceTicket correctly" in {
      highPriceTicket.fare shouldEqual trip2.cost * 1.5
    }
  }
}
package com.typesafe.training.scalatrain
package com.typesafe.training.scalatrain

import org.joda.time.LocalDate

import scala.collection.immutable.Seq

import TestData._
import org.scalatest.{ Matchers, WordSpec }


class ScheduleSpec extends WordSpec with Matchers {

  "Creating a Schedule" should {
    "isTrainRunning" should {
      "return true if train is running on a day without exception" in {
        val exceptionDates: Set[LocalDate] = Set(new LocalDate())
        val newScheduleFor724: Schedule = Schedule(
          Seq(
            (Seq(
            ice724MunichItem,
            ice724FrankfurtItem,
            ice724CologneItem,
            ice724NurembergItem
            ),
            Set(DayOfWeek("MON").get, DayOfWeek("TUE").get, DayOfWeek("WED").get, DayOfWeek("THU").get)
            )
          ),
          exceptionDates
        )
        newScheduleFor724.isTrainRunning(new LocalDate(2016, 5, 16)) shouldEqual true
      }

      "return false if train is not running on a day with exception" in {
        val exceptionDates: Set[LocalDate] = Set(new LocalDate(2016, 5, 16))
        val newScheduleFor724: Schedule = Schedule(
          Seq(
            (Seq(
              ice724MunichItem,
              ice724FrankfurtItem,
              ice724CologneItem,
              ice724NurembergItem
            ),
              Set(DayOfWeek("MON").get, DayOfWeek("TUE").get, DayOfWeek("WED").get, DayOfWeek("THU").get)
              )
          ),
          exceptionDates
        )
        newScheduleFor724.isTrainRunning(new LocalDate(2016, 5, 16)) shouldEqual false
      }

      "return false if train is not running on a day without exception, because Friday" in {
        val newScheduleFor724: Schedule = Schedule(
          Seq(
            (Seq(
              ice724MunichItem,
              ice724FrankfurtItem,
              ice724CologneItem,
              ice724NurembergItem
            ),
              Set(DayOfWeek("MON").get, DayOfWeek("TUE").get, DayOfWeek("WED").get, DayOfWeek("THU").get)
              )
          )
        )
        newScheduleFor724.isTrainRunning(new LocalDate(2016, 5, 18)) shouldEqual true
      }
    }
  }
}

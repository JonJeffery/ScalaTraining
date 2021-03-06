/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import org.joda.time.LocalDate

import scala.collection.immutable.Seq

object TestData {

  val munich = Station("Munich")

  val nuremberg = Station("Nuremberg")

  val frankfurt = Station("Frankfurt")

  val cologne = Station("Cologne")

  val essen = Station("Essen")

  val ice724MunichTime = Time(8, 50)

  val ice724NurembergTime = Time(10)

  val ice724FrankfurtTime = Time(12, 10)

  val ice724CologneTime = Time(13, 39)

  val ice726MunichTime = Time(7, 50)

  val ice726NurembergTime = Time(9)

  val ice726FrankfurtTime = Time(11, 10)

  val ice726CologneTime = Time(13, 2)

  val ice856MunichTime = Time(5, 0)

  val ice856NurembergTime = Time(7)

  val ice856FrankfurtTime = Time(9, 40)

  val ice856CologneTime = Time(11, 15)

  val ice724MunichItem = ScheduleItem(ice724MunichTime -> munich)
  val ice724NurembergItem = ScheduleItem(ice724NurembergTime -> nuremberg)
  val ice724FrankfurtItem = ScheduleItem(ice724FrankfurtTime -> frankfurt)
  val ice724CologneItem = ScheduleItem(ice724CologneTime -> cologne)
  val ice724Schedule: Schedule = Schedule(Seq(
    (Seq(
      ice724MunichItem,
      ice724NurembergItem,
      ice724FrankfurtItem,
      ice724CologneItem
    ),
      Set(DayOfWeek("MON").get, DayOfWeek("TUE").get, DayOfWeek("WED").get, DayOfWeek("THU").get
      ))))

  val ice724 = Train(
    InterCityExpress(724),
    ice724Schedule,
    5.0
  )

  val ice726MunichItem = ScheduleItem(ice726MunichTime -> munich)
  val ice726NurembergItem = ScheduleItem(ice726NurembergTime -> nuremberg)
  val ice726FrankfurtItem = ScheduleItem(ice726FrankfurtTime -> frankfurt)
  val ice726EssenItem = ScheduleItem(ice726CologneTime -> essen)

  val ice726Schedule = Schedule(Seq(
    (Seq(
      ice726MunichItem,
      ice726NurembergItem,
      ice726FrankfurtItem,
      ice726EssenItem
    ),
      Set(DayOfWeek("WED").get, DayOfWeek("FRI").get, DayOfWeek("SAT").get, DayOfWeek("SUN").get))))

  val ice726 = Train(
    InterCityExpress(726),
    ice726Schedule,
    10.0
  )

  val ice856EssenItem = ScheduleItem(ice856CologneTime -> essen)
  val ice856NurembergItem = ScheduleItem(ice856NurembergTime -> nuremberg)
  val ice856MunichItem = ScheduleItem(ice856MunichTime -> munich)
  val ice856FrankfurtItem = ScheduleItem(ice856FrankfurtTime -> frankfurt)

  val ice856Schedule = Schedule(
    Seq((Seq(
      ice856EssenItem,
      ice856NurembergItem,
      ice856MunichItem,
      ice856FrankfurtItem
      ),
      Set(DayOfWeek("MON").get, DayOfWeek("TUE").get, DayOfWeek("WED").get, DayOfWeek("FRI").get)
    )),
    Set(new LocalDate(2016, 5, 16))
    )

  val ice856 = Train(
    BavarianRegional(856),
    ice856Schedule,
    10.0
  )

  val planner = new JourneyPlanner(Set(ice724, ice726))

  val planner2 = new JourneyPlanner(Set(ice724, ice726, ice856))

  val hop = new Hop(munich, cologne, ice724)

  val hop_M_N_724 = new Hop(munich, nuremberg, ice724)
  val hop_N_F_724 = new Hop(nuremberg, frankfurt, ice724)
  val hop_F_C_724 = new Hop(frankfurt, cologne, ice724)

  val hop_M_N_726 = new Hop(munich, nuremberg, ice726)
  val hop_N_F_726 = new Hop(nuremberg, frankfurt, ice726)

  val trip1 = Trip(Seq(hop_M_N_724))
  val trip2 = Trip(Seq(hop_M_N_724, hop_N_F_724))

  val discountTicket1: Ticket = Ticket(trip1, new LocalDate(2016, 5, 16), new LocalDate(2016, 5, 16))
  val discountTicket2: Ticket = Ticket(trip2, new LocalDate(2016, 5, 16), new LocalDate(2016, 5, 16))

  val samePriceTicket: Ticket = Ticket(trip1, new LocalDate(2016, 5, 16), new LocalDate(2016, 5, 1))
  val highPriceTicket: Ticket = Ticket(trip2, new LocalDate(2016, 5, 16), new LocalDate(2016, 5, 14))

}

package com.typesafe.training.scalatrain

import org.joda.time.LocalDate
import org.joda.time.Days


/**
  * Created by jonj on 2016-03-10.
  */
case class Ticket(trip: Trip, bookingDate: LocalDate, dateBought: LocalDate = LocalDate.now) {
  val fare: BigDecimal = adjustFare(trip.cost)

  def adjustFare(nonAdjustedFare: BigDecimal) = {
    val daysBetween: Int = Days.daysBetween(dateBought, bookingDate).getDays

    if (daysBetween >= 14) {
      nonAdjustedFare
    } else if (daysBetween < 1) {
      nonAdjustedFare * .75
    } else {
      nonAdjustedFare * 1.5
    }
  }
}

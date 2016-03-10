package com.typesafe.training.scalatrain

/**
  * Created by denisg on 2016-03-09.
  */
case class Trip(hops: Seq[Hop]) {
  val travelTime: Int = hops.last.arrivalTime - hops.head.departureTime
  val cost: BigDecimal = hops.map(_.train.fare).sum
}

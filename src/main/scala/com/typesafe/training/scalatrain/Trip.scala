package com.typesafe.training.scalatrain

/**
  * Created by denisg on 2016-03-09.
  */
case class Trip(hops: Seq[Hop]) {
  val travelTime = hops.last.arrivalTime - hops.head.departureTime
  val cost = hops.map(_.train.fare).sum
}

package com.typesafe.training.scalatrain


/**
  * Created by jonj on 2016-03-08.
  */
case class Hop(from: Station, to: Station, train: Train) {
  require(train.stations.contains(from) && train.stations.contains(to))
  val departureTime: Time = train.timeAt(from).get
  val arrivalTime: Time = train.timeAt(to).get
}



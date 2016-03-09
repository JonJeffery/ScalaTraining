package com.typesafe.training.scalatrain

import scala.annotation.tailrec

/**
  * Created by jonj on 2016-03-07.
  */
class JourneyPlanner(trains: Set[Train]) {
  val stations: Set[Station] = trains.flatMap(_.stations)

  val hopMap: Map[Station, Set[Hop]] = {
    val hopSet: Set[Hop] = for {
      train <- trains
      (station1, station2) <- train.backToBack
    } yield Hop(station1, station2, train)

    hopSet.groupBy(_.from)
  }

  def trainsAt(station: Station): Set[Train] =
    trains.filter(_.stations.contains(station))

  def stopsAt(station: Station): Set[(Time, Train)] =
    for {
      train <- trains
      (time, aStation) <- train.schedule if aStation == station
    } yield (time, train)

  def isShortTrip(from:Station, to: Station): Boolean = {
    trains.exists(train => train.stations.dropWhile(_ != from) match {
      case `from` +: `to` +: rest => true
      case `from` +: other +: `to` +: rest => true
      case _ => false
    })
  }

//  def calculateConnections (from: Station, to: Station, departureTime: Time): Set[Seq[Hop]] = {


    //    val hopsFromFrom = hopMap.get(from).get.filter(_.departureTime == departureTime)
//    for {
//      hop <- hopsFromFrom
//      hop.to =
//
//    }

    //set of hops leaving "from"


//  }

//  def test(from: Station, to: Station, departureTime: Time): Set[Seq[Hop]] = {
//    @tailrec
//    def getPossibleTripsRec(start: Station, end: Station, startTime: Time, seenStations: Set[Station], acc: Set[Seq[Hop]]): Unit = {
//
//    }
//  }

}

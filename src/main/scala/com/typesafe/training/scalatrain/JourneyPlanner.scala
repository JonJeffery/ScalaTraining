package com.typesafe.training.scalatrain

import scala.annotation.tailrec
import scala.collection.{:+, mutable}


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

    hopSet.groupBy(_.from).withDefaultValue(Set())
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

  def calculateConnections(from: Station, to: Station, departureTime: Time): Set[Seq[Hop]] = {

    def findHopRecursive (hop: Hop, currentRoute: Seq[Hop], visitedStations: Set[Station]): Set[Seq[Hop]] = {
      if (hop.to == to)
        Set(currentRoute :+ hop)
      else
      for {
          newHop <- getHopsDepartingFromStationAtTime(hop.to, hop.arrivalTime).filter(hop => !visitedStations(hop.to))
          goodPath <- findHopRecursive(newHop, currentRoute :+ hop, visitedStations + hop.to)
        } yield
          goodPath
    }
    for {
      hop <- getHopsDepartingFromStationAtTime(from, departureTime)
      path <- findHopRecursive(hop, Seq(), Set(hop.from) )
    } yield path
  }

  def getHopsDepartingFromStationAtTime(from: Station, departureTime: Time): Set[Hop] = {
    hopMap(from).filter(_.departureTime >= departureTime)
  }
}

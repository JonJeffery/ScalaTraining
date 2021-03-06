package com.typesafe.training.scalatrain

import org.joda.time.LocalDate


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
      scheduleItem <- train.schedule.scheduleItems if scheduleItem.station == station
    } yield (scheduleItem.time, train)

  def isShortTrip(from: Station, to: Station): Boolean = {
    trains.exists(train => train.stations.dropWhile(_ != from) match {
      case `from` +: `to` +: rest => true
      case `from` +: other +: `to` +: rest => true
      case _ => false
    })
  }

  def calculatePathsBetweenStations(from: Station, to: Station, departureTime: Time): Set[Trip] = {

    def findHopByTimeRecursive(hop: Hop, currentRoute: Seq[Hop], visitedStations: Set[Station]): Set[Trip] = {
      if (hop.to == to)
        Set(Trip(currentRoute :+ hop))
      else
        for {
          newHop <- getHopsDepartingFromStationAtTime(hop.to, hop.arrivalTime).filter(hop => !visitedStations(hop.to))
          goodPath <- findHopByTimeRecursive(newHop, currentRoute :+ hop, visitedStations + hop.to)
        } yield
          goodPath
    }
    for {
      hop <- getHopsDepartingFromStationAtTime(from, departureTime)
      path <- findHopByTimeRecursive(hop, Seq(), Set(hop.from))
    } yield path
  }

  def sortPathsByTotalTime(paths: Set[Trip]): Seq[Trip] = {
    paths.toList.sortBy(_.travelTime)
  }

  def sortPathsByTotalCost(paths: Set[Trip]): Seq[Trip] = {
    paths.toList.sortBy(_.cost)
  }

  def getHopsDepartingFromStationAtTime(from: Station, departureTime: Time): Set[Hop] = {
    hopMap(from).filter(_.departureTime >= departureTime)
  }

  def getHopsDepartingFromStationOnDate(from: Station, date: LocalDate): Set[Hop] = {
    hopMap(from).filter(hop => hop.train.schedule.isTrainRunning(date))
  }

  def getTrainsRunningOn(date: LocalDate): Set[Train] = {
    trains.filter(_.schedule.isTrainRunning(date))
  }

  def findTripsBetweenStationsOnDate(from: Station, to: Station, date: LocalDate): Set[Trip] = {

    def findHopByDateRecursive(hop: Hop, currentRoute: Seq[Hop], visitedStations: Set[Station]): Set[Trip] = {
      if (hop.to == to && hop.train.schedule.isTrainRunning(date))
        Set(Trip(currentRoute :+ hop))
      else
        for {
          newHop <- getHopsDepartingFromStationOnDate(hop.to, date).filter(hop => !visitedStations(hop.to))
          goodPath <- findHopByDateRecursive(newHop, currentRoute :+ hop, visitedStations + hop.to)
        } yield
          goodPath
    }
    for {
      hop <- getHopsDepartingFromStationOnDate(from, date)
      path <- findHopByDateRecursive(hop, Seq(), Set(hop.from))
    } yield path
  }

  def getSinkStations(): Set[Station] = {
    stations diff hopMap.keySet
  }


}

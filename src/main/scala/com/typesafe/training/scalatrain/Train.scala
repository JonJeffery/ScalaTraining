package com.typesafe.training.scalatrain

import org.joda.time.LocalDate

import scala.collection.immutable.Seq

/**
  * Created by jonj on 2016-03-07.
  */
case class Train(info: TrainInfo, schedule: Schedule, fare: BigDecimal = 0.0) {
  require(schedule.stations.size >= 2)
  val stations: Seq[Station] = schedule.stations

  val backToBack: Seq[(Station, Station)] = stations.zip(stations.tail)

  val departureTimes: Seq[(Station, Time)] = schedule.scheduleItems.map(time => (time.station, time.time)).toList

  var lastMaintenanceDate: LocalDate = new LocalDate(1900, 1, 1)

  def timeAt(station: Station): Option[Time] = {
    schedule.scheduleItems.find(_.station == station).map(_.time)
  }
}

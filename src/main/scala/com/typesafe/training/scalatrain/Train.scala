package com.typesafe.training.scalatrain
import scala.collection.immutable.Seq

/**
  * Created by jonj on 2016-03-07.
  */
case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  //TODO  Verify that schedule is strictly increasing in time
  require(schedule.size >= 2)
  val stations: Seq[Station] = schedule.map(station => station._2)

  val backToBack: Seq[(Station, Station)] = stations.zip(stations.tail)

  val departureTimes: Seq[(Station, Time)] = schedule.map(_.swap)

  def timeAt(station: Station): Option[Time] = {
    schedule.find(_._2 == station).map(_._1)


    /**
      * schedule.collectFirst({
      *   case(time, `station`) => time
      * })
      *  is also a valid option here
      */
  }
}

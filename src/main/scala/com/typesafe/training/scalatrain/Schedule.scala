package com.typesafe.training.scalatrain
import scala.collection.immutable.Seq


import org.joda.time.LocalDate


/**
  * Created by jonj on 2016-03-09.
  */
case class Schedule(scheduledTimes: Seq[((Seq[ScheduleItem]), Set[DayOfWeek])],
  exceptionDates: Set[LocalDate] = Set()) {
  val stations: Seq[Station] = scheduledTimes.flatMap(_._1).map(_.station)
  val daysOfWeek: Set[DayOfWeek] = scheduledTimes.flatMap(_._2).toSet
  val scheduleItems: Seq[ScheduleItem] = scheduledTimes.flatMap(_._1)

  def isTrainRunning(date: LocalDate): Boolean = {
      val d = DayOfWeek(date.dayOfWeek.get())
      d match {
        case Some(day) => daysOfWeek(day) && !exceptionDates.contains(date)
        case _ => false
    }
  }
}

case class ScheduleItem(item: (Time, Station)) {
  val time = item._1
  val station = item._2
}


package com.typesafe.training.scalatrain

/**
  * Created by jonj on 2016-03-10.
  */
sealed trait DayOfWeek
case object Monday extends DayOfWeek
case object Tuesday extends DayOfWeek
case object Wednesday extends DayOfWeek
case object Thursday extends DayOfWeek
case object Friday extends DayOfWeek
case object Saturday extends DayOfWeek
case object Sunday extends DayOfWeek

object DayOfWeek {
  def unapply(s: String): Option[DayOfWeek] = s.toUpperCase match {
    //    case Int(x) => x match {
    //      case 1 => Some(Sunday)
    //      // ...
    //      case _ => None
    //    }
    case "MON" | "MONDAY" => Some(Monday)
    case "TUE" | "TUESDAY" => Some(Monday)
    case "WED" | "WEDNESDAY" => Some(Monday)
    case "THU" | "THURSDAY" => Some(Monday)
    case "FRI" | "FRIDAY" => Some(Monday)
    case "SAT" | "SATURDAY" => Some(Monday)
    case "SUN" | "SUNDAY" => Some(Sunday)
    case _ => None
  }

  def apply(s: String): DayOfWeek = s match {
    case DayOfWeek(d) => d
    case _ => throw new IllegalArgumentException(
      "Invalid value for day of week: " + s)
  }
}
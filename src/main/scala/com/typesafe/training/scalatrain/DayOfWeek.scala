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
  def apply(s: String): Option[DayOfWeek] = s.toUpperCase match {
    case "MON" | "MONDAY" => Some(Monday)
    case "TUE" | "TUESDAY" => Some(Tuesday)
    case "WED" | "WEDNESDAY" => Some(Wednesday)
    case "THU" | "THURSDAY" => Some(Thursday)
    case "FRI" | "FRIDAY" => Some(Friday)
    case "SAT" | "SATURDAY" => Some(Saturday)
    case "SUN" | "SUNDAY" => Some(Sunday)
    case _ => None
  }

  def apply(i: Int): Option[DayOfWeek] = i match {
    case 1 => Some(Monday)
    case 2 => Some(Tuesday)
    case 3 => Some(Wednesday)
    case 4 => Some(Thursday)
    case 5 => Some(Friday)
    case 6 => Some(Saturday)
    case 7 => Some(Sunday)
    case _ => None
  }
}
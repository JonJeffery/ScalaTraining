package com.typesafe.training.scalatrain

import play.api.libs.json
import play.api.libs.json.{JsNumber, JsObject, Json, JsValue}
import scala.util.{Success, Failure}
import scala.util.Try

/**
  * Created by jonj on 2016-03-07.
  */

object Time {

  def fromMinutes(minutes: Int): Time = {
    Time(minutes / 60, minutes % 60)
  }

  def fromJson(jsonData: JsValue): Option[Time] = {
    Try((jsonData \ "hours").as[Int]) match {
      case Success(newHours) => {

        Try((jsonData \ "minutes").as[Int]) match {
          case Success(newMinutes) => Some(Time(newHours, newMinutes))
          case Failure(_) => Some(Time(newHours, 0))
        }
      }
      case Failure(_) => None
    }
  }

//  def fromJson2(json: JsValue): Option[Time] = {
//    val tryTime = for {
//      hours <- Try((json \ "hours").as[Int])
//      minutes <- Try((json \ "minutes").as[Int]).getOrElse(0)
//    } yield Time(hours, minutes)
//    tryTime.toOption
//  }
}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(0 <= hours && hours < 24, "Hours is outside of acceptable range of 0-23.")
  require(0 <= minutes && minutes < 60, "Minutes is outside of acceptable range of 0-59")

  val asMinutes: Int = hours * 60 + minutes

  override lazy val toString: String =
    f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    asMinutes - that.asMinutes

  def -(that: Time): Int =
    this.minus(that)

  def compare(that: Time): Int =
    this - that

  def toJson: JsValue = {
    Json.obj("hours" -> hours, "minutes" -> minutes)
  }

  def fromJson(jsonData: JsValue): Option[Time] = {
    Try((jsonData \ "hours").as[Int]) match {
      case Success(newHours) => {

        Try((jsonData \ "minutes").as[Int]) match {
          case Success(newMinutes) => Some(Time(newHours, newMinutes))
          case Failure(_) => Some(Time(newHours, 0))
        }
      }
      case Failure(_) => None
    }
  }
}

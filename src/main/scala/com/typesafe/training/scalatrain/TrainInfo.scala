package com.typesafe.training.scalatrain

/**
  * Created by jonj on 2016-03-07.
  */
sealed abstract class TrainInfo {
  def number: Int
}
case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo
case class RegionalExpress(number: Int) extends TrainInfo
case class BavarianRegional(number: Int) extends TrainInfo



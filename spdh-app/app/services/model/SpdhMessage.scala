package services.model

/**
  * Created by henry on 2017-11-26.
  */
trait SpdhMessage {
  def payload: String
}

case class W34Request(payload: String) extends SpdhMessage
case class W35Request(payload: String) extends SpdhMessage
case class W99Request(payload: String) extends SpdhMessage
case class W34Response(payload: String) extends SpdhMessage
case class W35Response(payload: String) extends SpdhMessage
case class W99Response(payload: String) extends SpdhMessage

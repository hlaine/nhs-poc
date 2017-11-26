package services.actors

import akka.actor._
import services.model._

/**
  * Created by henry on 2017-11-26.
  */
object SpdhActor {
  def props = Props[SpdhActor]
}

class SpdhActor extends Actor with ActorLogging {

  override def receive: Receive = {
    case W34Request(payload) => sender ! W34Response(payload.reverse)
    case W35Request(payload) => sender ! W35Response(payload.toUpperCase)

  }

}

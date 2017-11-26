package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import com.google.inject.Singleton
import play.api.mvc.{Action, Controller}
import services.actors.SpdhActor
import services.model._
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SpdhController @Inject() (actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends Controller  {

  val spdhActor = actorSystem.actorOf(SpdhActor.props, "spdh-actor")
  implicit val timeout = Timeout(3 seconds)

  def handleSpdhMessage(raw: String) = Action.async { implicit request =>
    parse(raw) match {
      case Some(msg) => (spdhActor ? msg) map {
        case response:SpdhMessage => Ok(response.payload)
        case _ => InternalServerError("Sorry!")
      }
      case _ => Future(BadRequest("Won't do!"))
    }
  }

  private def parse(raw: String): Option[SpdhMessage] = {
    if (raw.startsWith("W34")) Some(W34Request(raw))
    else if (raw.startsWith("W35")) Some(W35Request(raw))
    else if (raw.startsWith("W99")) Some(W99Request(raw))
    else None
  }
}

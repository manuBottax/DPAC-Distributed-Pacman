package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.utils.{ActorUtils, ControllerObservable}

import scala.util.parsing.json.JSONObject

/**
  * This actor manage the message, that it's received from server.
  *
  * @author Giulia Lucchi
  */
class FromServerCommunication extends UntypedAbstractActor{
  val controllerObservable = ControllerObservable

  override def onReceive(message: Any): Unit = message match {
    case msg: JSONObject => msg.obj("object") match {
      case "friendRequest" =>{
        val username = msg.obj("senderRequest").asInstanceOf[String]
        controllerObservable.gameRequest("GameRequest", username)
      }
      case "friendResponse" =>{
        val response = msg.obj("responseRequest").asInstanceOf[Boolean]
        controllerObservable.gameResponse("GameResponse", response)
      }
      case _ => { val receiver = context actorSelection ActorUtils.INBOX_ACTOR
        receiver ! msg.asInstanceOf[JSONObject]

      }

    }
  }
}
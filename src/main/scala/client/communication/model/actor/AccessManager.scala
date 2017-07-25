package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.model.MatchResult

import scala.util.parsing.json.JSONObject

/**
  * The actor manages the connection, registration, login and logout.
  *
  * @author Giulia Lucchi
  */
class AccessManager extends UntypedAbstractActor {
  override def onReceive(message: Any): Unit = message match {
    case msg: JSONObject =>msg.obj("object") match{
      case "newUser" => {
        val receiver = context.system actorSelection "user/toServerCommunication"
        receiver ! msg.asInstanceOf[JSONObject]
        //receiver ! true  //PER PROVARE FUNZIONAMENTO Inbox
      }
      case "login" =>{
        val receiver = context.system actorSelection "user/toServerCommunication"
        receiver ! msg.asInstanceOf[JSONObject]
      }
      case "matches" => {
        var allMatches: Option[List[MatchResult]] = msg.obj("list").asInstanceOf[Option[List[MatchResult]]]
        val receiver = context.system actorSelection "/system/dsl/inbox-1"
        receiver ! allMatches
      }
    }

    case msg: Boolean => {
      val receiver = context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[Boolean]
    }
  }

}
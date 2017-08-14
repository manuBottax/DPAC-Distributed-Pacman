package main

import javax.swing.SwingUtilities

import client.communication.model.ToClientCommunicationImpl
import client.controller._
import client.model.peerCommunication.{ClientIncomingMessageHandler, ClientIncomingMessageHandlerImpl, ClientOutcomingMessageHandler, ClientOutcomingMessageHandlerImpl}
import client.view.MainFrame

object Main extends App {
  println("[ DPACS - Distributed Pacman ]")
  println("[ Version 1.0.0 - August 2017 ]")
  println()
  println("[ Project for Software System Development course @ Unibo - Ingegneria e Scienze Informatiche - A.Y. 2016/17 ]")
  println("[ Developed by Manuel Bottazzi, Giulia Lucchi, Federica Pecci, Margherita Pecorelli & Chiara Varini ]")
  println()

  val controllerCharacter: ControllerCharacter = BaseControllerCharacter.instance
  val controllerMatch: ControllerMatch = BaseControllerMatch.instance
  val controllerUser: ControllerUser = BaseControllerUser.instance

  val model = ToClientCommunicationImpl()

  val ci: ClientIncomingMessageHandler = new ClientIncomingMessageHandlerImpl
  val co: ClientOutcomingMessageHandler = new ClientOutcomingMessageHandlerImpl

  controllerMatch.model(model)
  controllerUser.model(model)

  ci.addObserver(controllerCharacter)
  co.addObserver(controllerMatch)

  SwingUtilities.invokeLater(new Runnable() {
    override def run(): Unit = {
      MainFrame.getInstance
    }
  })

}

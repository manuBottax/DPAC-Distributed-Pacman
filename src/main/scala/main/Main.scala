package main

import javax.swing.SwingUtilities

import client.communication.model.ToClientCommunicationImpl
import client.controller._
import client.utils.ActorUtils
import client.view.MainFrame


/**
  * Application main.
  */
object Main extends App {
  println("[ DPACS - Distributed Pacman ]")
  println("[ Version 1.0.0 - August 2017 ]")
  println()
  println("[ Project for Software System Development course @ Unibo - Ingegneria e Scienze Informatiche - A.Y. 2016/17 ]")
  println("[ Developed by Manuel Bottazzi, Giulia Lucchi, Federica Pecci, Margherita Pecorelli & Chiara Varini ]")
  println()

  if (args.length == 0) {
    println("You didn't insert server IP number")
  } else {
    ActorUtils.serverIP = args(0)
    val controllerCharacter: ControllerCharacter = BaseControllerCharacter
    val controllerMatch: ControllerMatch = BaseControllerMatch
    val controllerUser: ControllerUser = BaseControllerUser

    val model = ToClientCommunicationImpl()

    controllerMatch.setModel(model)
    controllerUser.setModel(model)

    SwingUtilities.invokeLater(new Runnable() {
      override def run(): Unit = {
        MainFrame.getInstance
      }
    })
  }

}

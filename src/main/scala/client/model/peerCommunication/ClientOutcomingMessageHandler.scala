package client.model.peerCommunication

import java.util.Observer

/**
  * Created by Federica on 24/07/17.
  *
  * this trait is useful to handle two types of communication:
  * - peer to peer (so that, a peer send info to others)
  * - peer to controller
  *
  */

trait ClientOutcomingMessageHandler  {

  /**
    * this method allows to register controller
    * as model observer, so that:
    * - model is observable
    * - controller is observer of model
    * @param observer
    */
  def addObserver(observer: Observer): Unit

  /**
    * method to notify controller and other peers
    * about the number of lives left to this peer
    * @param arg
    */
  def notifyRemainingLives(arg: scala.Any): Unit

  /**
    * method to notify controller and other peers
    * about the current total score of this peer
    * @param arg
    */
  def notifyScore(arg: scala.Any): Unit

  /**
    * method to notify controller and other peers
    * about the current position of this peer
    * @param arg
    */
  def notifyMove(arg: scala.Any): Unit

  /**
    * method to notify controller and other peers
    * about the current state (dead or alive) of this peer
    * @param arg
    */
  def notifyDeath(arg: scala.Any): Unit



}
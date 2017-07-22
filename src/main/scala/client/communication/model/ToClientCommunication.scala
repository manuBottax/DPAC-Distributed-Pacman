package client.communication.model

import java.awt.Image
import java.io.File
import java.util.Observer

import client.model.{Direction, MatchResult}

/**
  * This class manages the model of communication between Client and Server.
  *
  * @author Giulia Lucchi
  */
trait ToClientCommunication {

  /**
    * Send the message to actor AccessManager with the registration's data and
    * receive from server the response.
    *
    * @param name
    * @param username
    * @param email
    * @param password
    * @param confirmPassword
    * @return true  if registration ended good
    *         false otherwise
    */
  def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean

  /**
    * Send the message to actor AccessManager with the login's data and
    * receive from sever the response with also the MatchResult
    * @param username
    * @param Password
    * @return list of MatchResult with data, result and score.
    *         If it's 'None', the registration ended not good.
    *         If it's Option.empty, this is the first login
    */
  def login(username: String, Password: String): Option[List[MatchResult]]

  /**
    *  Send to server the list of range to play the match.
    *
    * @return list of range to players' game
    */
  def getRanges: List[Range]
  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
    */
  def getCharactersToChoose: Map[String, Image]

  /**
    * Receives from server the characters playing in the current match
    * NON SONO RICHIAMATI DAL CONTROLLER
    *
    * @return list of current match's characters.
    *         The Map has the name of character as key and, as value, a Map with direction and Image.
    */
  def getTeamCharacter: Map[String, Map[Direction, Image]]

  /**
    * Send to server the character chosen. It's recall when the player choose him character.
    *
    * @param character character chosen from single player
    *
    * @return true  if character has been already chosen
    *         false otherwise
    */
  def chooseCharacter(character: Character): Boolean

  /**
    * Receives from server the List of available playgrounds.
    *
    * @return list of available playgrounds
    */
  def getPlaygrounds: List[File]

  /**
    * Send to server the playground chosen. It's recall when the player choose the playground of current match.
    *
    * @param playground position of playground's in the file list.
    *
    */
  def choosePlayground(playground: Int): Unit


  /**
    * Send to server the match just ended.
    *
    * @param result  The MatchResult with date and score of the ended match
    * @param user id of characters.
    */
  def MatchResult(result: MatchResult, user: String): Unit

  /**
    * Adds the observer.
    *
    * @param observer observer to add.
    */
  def addObserver(observer: Observer): Unit

  /**
    * Receives from server playgrond's string, corresponding to chosen playground.
    * SONO  SERVE AL CONTROLLER
    * @return Playground chosen in current match
    */
  def playgroundChosen(): String

  /**
    * Receives from server all the played matches of selected username
    *
    * @param username username of player
    * @return list of all match with its result
    */
  def getAllMatchesResults(username: String): List[MatchResult]
}

package client.model.character

import java.lang.Integer.valueOf

import alice.tuprolog.Term
import client.model.utils.{Point, PointImpl, PrologConfig}

/**
  * Extraction of starting information of the game
  *
  * @author Giulia Lucchi
  */
trait InitializedInfo{
  /**
    *
    * Extracts from the logic of the game implemented in prolog
    * the ghost's or pacman's number of lives
    *
    * @param characterName  the goal in prolog
    * @return number of lives of ghost
  **/
  def getCharacterLives(characterName: String): Int


  /**
    * Extracts from the logic of the game implemented in prolog
    * the pacman's starting position
    *
    * @return the coordinate of pacman's starting position
    */
  def getPacmanStartPosition(): Point[Int, Int]

  /**
    * Extracts from the logic of the game implemented in prolog
    * the ghost's starting position
    *
    * @return the coordinate of ghost's starting position
    */
  def getGhostStartPosition(): Point[Int,Int]
}

object InitializedInfoImpl extends InitializedInfo{

  /**
    *
    * Extracts from the logic of the game implemented in prolog
    * the ghost's or pacman's number of lives
    *
    * @param characterName the name of character
    * @return number of lives of ghost
    **/
  override def getCharacterLives(characterName: String): Int = {
    var term: Term =  null
    characterName match {
      case "ghost" => term = PrologConfig.engine.solve(Term.createTerm("ghost_lives(X)")).getTerm("X")
      case "pacman" => term = PrologConfig.engine.solve(Term.createTerm("pacman_lives(X)")).getTerm("X")
    }
    valueOf(term.toString)
  }

  /**
    * Extracts from the logic of the game implemented in prolog
    * the pacman's starting position
    *
    * @return the coordinate of pacman's starting position
    */
  override def getPacmanStartPosition(): Point[Int, Int] ={
    val position =  PrologConfig.engine.solve("pacman_initial_position(X,Y).")
    var xPos =  valueOf(position.getTerm("X").toString)
    var yPos = valueOf(position.getTerm("Y").toString)
    PointImpl[Int, Int](xPos, yPos)
  }

  val previousPosition =   PrologConfig.engine.solve("ghost_initial_position(X,Y).")
  var xPosition =  valueOf(previousPosition.getTerm("X").toString)
  var yPosition = valueOf(previousPosition.getTerm("Y").toString)

  /**
    * Extracts from the logic of the game implemented in prolog
    * the ghost's starting position
    *
    * @return the coordinate of ghost's starting position
    */
  override def getGhostStartPosition(): Point[Int,Int] ={

    val term = PrologConfig.engine.solve(Term.createTerm("next_position("+xPosition+","+yPosition+",X,Y)")).getTerm("X")
    val value: Int = valueOf(term.toString)
    xPosition = value
    PointImpl[Int, Int](value, yPosition)
  }

}
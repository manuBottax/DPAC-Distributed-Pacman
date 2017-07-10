package client.model.character

import client.model.Direction
import client.model.character.gameElement.GameItem
import client.model.utils.{Point, PointImpl}

/**
  * Manages the base model of client.model.character.gameElement.character.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
trait Character[X,Y] extends GameItem{
  /**
    * Manages the client.model.character.gameElement.character's movement and consequently the contact with other item of the game.
    *
    * @param direction    client.model.character.gameElement.character's of direction
    */
  def go(direction: Direction): Unit

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  def checkAllPositions(): Unit

  /**
    * setter client.model.character.gameElement.character's position
    *
    * @param point    a point of client.model.character.gameElement.character within the game map
    * */
  def setPosition(point: Point[X,Y]): Unit

  /**
    * getter of client.model.character.gameElement.character's direction
    *
    * @return direction   a direction of client.model.character.gameElement.character
    * */
  def direction: Direction

  /**
    * setter of client.model.character.gameElement.character's direction
    *
    * @param direction    a direction of client.model.character.gameElement.character
    * */
  def direction_=(direction: Direction): Unit

  /**
    * getter of client.model.character.gameElement.character's state of its life
    *
    * @return true    if client.model.character.gameElement.character is alive
    *         false   otherwise
    * */
  def isAlive: Boolean

  /**
    * setter of client.model.character.gameElement.character's state of its life
    *
    * @param isAlive   true    if client.model.character.gameElement.character is alive
    *                  false   otherwise
    * */
  def isAlive_=(isAlive: Boolean): Unit

  /**
    * getter if the client.model.character.gameElement.character is killer or killable
    *
    * @return true    if client.model.character.gameElement.character is killable of other client.model.character.gameElement.character
    *         false   otherwise
    * */
  def isKillable: Boolean

  /**
    * setter if the client.model.character.gameElement.character is killer or killable
    *
    * @return true    if client.model.character.gameElement.character is killable of other client.model.character.gameElement.character
    *         false   otherwise
    * */
  def isKillable_=(isKillable: Boolean): Unit

  /**
    * getter of client.model.character.gameElement.character's name
    *
    * @return name    client.model.character.gameElement.character's name
    */
  def name: String

  /**
    * getter of lives in the single match
    *
    * @return lives   lives of the game in the sigle match
    */
  def lives: Lives

  /**
    * getter of current client.model.character.gameElement.character's score
    *
    * @return current client.model.character.gameElement.character's score
    */
  def score: Int

  /**
    * Setter of current chararcter's score
    *
    * @param score incremented score
    */
  def score_=(score: Int): Unit
}

abstract class CharacterImpl(override var isKillable: Boolean) extends Character[Int, Int] {

  private var pos: Point[Int, Int] = PointImpl(0,0)

  override var isAlive: Boolean = true
  override var direction: Direction = Direction.START
  override var score: Int = 0

  /**
    * Manages the client.model.character.gameElement.character's movement.
    *
    * @param direction - client.model.character.gameElement.character's direction
    */
  override def go(direction: Direction): Unit = {
    val point: Option[Point[Int, Int]] = move(direction)
    if (point nonEmpty) {
      setPosition(point get)
      checkAllPositions()
    } else {
      println("NO. it hit the wall.")
    }
  }

  /**
    * Recall the predicate about the client.model.character.gameElement.character's movement .
    *
    * @param direction the client.model.character.gameElement.character's direction during the movement
    * @return the new client.model.character.gameElement.character's position after the movement
    */
  private def move(direction: Direction): Option[Point[Int, Int]] = {
    val solveInfo = PrologConfig.getPrologEngine().solve(s"move(${pos x}, ${pos y},${direction getDirection}, X, Y).")
    if (solveInfo isSuccess) {
      val x = Integer.valueOf(solveInfo.getTerm("X").toString)
      val y = Integer.valueOf(solveInfo.getTerm("Y").toString)
      Option(PointImpl[Int, Int](x, y))
    }else{None}
  }

  /**
    * Returns the position of the item in the playground.
    *
    * @return item's position.
    */
  override def position: Point[Int, Int] = pos

   /**
     * setter client.model.character.gameElement.character's position
     *
     * @param point a point of client.model.character.gameElement.character within the game map
     **/
   override def setPosition(point: Point[Int, Int]): Unit = pos = point
 }
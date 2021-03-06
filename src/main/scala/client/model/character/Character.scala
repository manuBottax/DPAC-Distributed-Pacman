package client.model.character

import client.model._
import client.model.gameElement.GameItem
import client.model.utils._

/**
  * Represents all characters' behaviors.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
trait Character extends GameItem{

  /**
    * Manages character's movement and consequently the contact with other item of the game.
    *
    * @param direction - direction of character's movement.
    */
  def go(direction: Direction): Unit

  /**
    * Checks if the character can eat another character or if it can be eaten by another character.
    */
  def checkAllPositions: Unit

  /**
    * Sets character's position.
    *
    * @param position - the new position.
    */
  def setPosition(position: Point[Int,Int]): Unit

  /**
    * Returns last character's direction.
    *
    * @return last character's direction.
    */
  def direction: Direction

  /**
    * Sets direction of the character's movement.
    *
    * @param direction - movement's direction.
    */
  def direction_=(direction: Direction): Unit

  /**
    * Returns if character is alive.
    *
    * @return true if character is alive, false otherwise.
    */
  def isAlive: Boolean

  /**
    * Sets if charater is alive.
    *
    * @param isAlive - true if character is alive, false otherwise.
    */
  def isAlive_=(isAlive: Boolean): Unit

  /**
    * Returns if character is killable. If it is not, then it is a killer.
    *
    * @return true if character is killable, false if it is a killer.
    */
  def isKillable: Boolean

  /**
    * Sets if the character is killable.
    *
    * @param isKillable - true if character is killable, false if it is a killer.
    */
  def isKillable_=(isKillable: Boolean): Unit

  /**
    * Returns character's name.
    *
    * @return character's name.
    */
  def name: String

  /**
    * Returns character's lives.
    *
    * @return character's lives.
    */
  def lives: Lives

  /**
    * Sets character's lives.
    *
    * @param lives - character's lives.
    */
  def lives_=(lives: Lives): Unit

  /**
    * Returns character's score.
    *
    * @return character's score.
    */
  def score: Int

  /**
    * Sets character's score.
    *
    * @param score - the new character's score.
    */
  def score_=(score: Int): Unit

  /**
    * Returns if the character won.
    *
    * @return true if the character won, false otherwise.
    */
  def won: Boolean

  /**
    * Sets if the character won.
    *
    * @param won - true if character won, false otherwise.
    */
  def won_=(won: Boolean): Unit

  /**
    * Returns if the character had lost.
    *
    * @return true if the character had lost, false otherwise.
    */
  def hasLost: Boolean

  /**
    * Sets if the character has lost.
    *
    * @param hasLost - true if character has lost, false otherwise.
    */
  def hasLost_=(hasLost: Boolean): Unit

}

/**
  * Represents the implementation of all characters' behaviors.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
abstract class CharacterImpl(override var isKillable: Boolean, override var lives: Lives) extends Character {

  private val playground: Playground = PlaygroundImpl
  private val game: Match = MatchImpl
  private var _position: Point[Int, Int] = PointImpl(0,0)
  private var _isAlive = true
  private var _won = false

  override var direction = Direction.START
  override var score = 0
  override var hasLost = false

  /**
    * Manages character's movement and consequently the contact with other item of the game.
    *
    * @param direction - direction of character's movement.
    */
  override def go(direction: Direction) = {
    this.direction = direction
    val point: Option[Point[Int, Int]] = move(direction)
    if(point nonEmpty) {
      setPosition(point.get)
      checkAllPositions
    } else {
      println("Can't go in that direction")
    }
  }

  /**
    * Returns an Option containing character's new position if the movement is allowed, containing None otherwise.
    *
    * @param direction - the direction of character's movement.
    * @return an Option containing character's new position if the movement is allowed, containing None otherwise.
    */
  private def move(direction: Direction): Option[Point[Int, Int]] = {
    val solveInfo = PrologConfig.getPrologEngine.solve(s"move(${_position.x.toString},${_position.y.toString},${direction.getDirection},X,Y).")
    if(solveInfo isSuccess) {
      val x = Integer.valueOf(solveInfo.getTerm("X").toString)
      val y = Integer.valueOf(solveInfo.getTerm("Y").toString)
      Option(PointImpl[Int, Int](x, y))
    } else {
      None
    }
  }

  /**
    * Returns the position of the item in the playground.
    *
    * @return item's position.
    */
  override def position = _position

  /**
    * Sets character's position.
    *
    * @param position - the new position.
    */
  override def setPosition(position: Point[Int, Int]) = {
    playground.checkPosition(position)
    _position = position
  }

  /**
    * Returns if character is alive.
    *
    * @return true if character is alive, false otherwise.
    */
  override def isAlive = {
    if(lives.remainingLives <= 0) {
      _isAlive = false
      hasLost = true
    }
    _isAlive
  }

  /**
    * Sets if charater is alive.
    *
    * @param alive - true if character is alive, false otherwise.
    */
  override def isAlive_=(alive: Boolean) = _isAlive = alive

  /**
    * Returns if the character won.
    *
    * @return true if the character won, false otherwise.
    */
  def won = _won

  /**
    * Sets if the character won.
    *
    * @param won - true if character won, false otherwise.
    */
  def won_=(won: Boolean) = _won = won

  /**
    * Returns a string representing the prolog ghosts list containing all match's ghosts. It can be pass as Term in prolog.
    *
    * @return a string representing the prolog ghosts list containing all match's ghosts.
    */
  protected def prologGhostsList: String = {
    var ghosts: String = "["
    game.allCharacters.filter(c => !c.isInstanceOf[Pacman]).foreach{ e =>
      ghosts = ghosts + "ghost(" + e.position.x + "," + e.position.y + "," + e.score + "," + e.name + "),"
    }
    ghosts.length match {
      case 1 =>
        ghosts = ghosts + "]"
      case _ =>
        ghosts = ghosts.substring(0,ghosts.length-1)
        ghosts = ghosts + "]"
    }
    ghosts
  }

}

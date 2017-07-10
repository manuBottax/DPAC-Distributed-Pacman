package client.gameElement

import client.utils.Point

/**
  * Created by margherita on 10/07/17.
  */
case class Dot(override val id: String, override val position: Point[Int, Int]) extends Eatable {
  /**
    * Returns the value that is given as score when Pacman eat that item.
    *
    * @return the score value.
    */
  override def score: Int = Dot.score

  /**
    * Returns the family to which the eatable object belongs
    *
    * @return object's family
    */
  override def belonginFamily: String = "dot"
}

object Dot {
  private val score = 10
}
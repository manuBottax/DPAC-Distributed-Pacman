package client.gameElement

import client.utils.Point

/**
  * Created by margherita on 10/07/17.
  */
case class Grapes(override val id: String, override val position: Point[Int, Int]) extends Fruit(id,position){
  /**
    * Returns the value that is given as score when Pacman eat that item.
    *
    * @return the score value.
    */
  override def score: Int = Grapes.score
}

object Grapes {
  private val score = 1000
}

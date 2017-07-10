package client.gameElement

import client.utils.Point

/**
  * Created by margherita on 10/07/17.
  */
case class Apple(override val id: String, override val position: Point[Int, Int]) extends Fruit(id,position){
  /**
    * Returns the value that is given as score when Pacman eat that item.
    *
    * @return the score value.
    */
  override def score: Int = Apple.score
}

object Apple {
  private val score = 700
}
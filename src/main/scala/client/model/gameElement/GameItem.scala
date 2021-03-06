package client.model.gameElement

import client.model.utils.Point

/**
  * A generic game item trait, represent every element in the map.
  *
  * @author ManuBottax
  */
trait GameItem {

  /**
    * Returns the position of the item in the playground.
    *
    * @return item's position.
    */
  def position: Point[Int,Int]
}
package client

import client.model.character.gameElement. VirtualPill
import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */
class VirtualPillTest extends FunSuite{
  test("A New Pill Position is correctly available") {
    val p: VirtualPill = VirtualPill(PointImpl(5,6))

    assert(p.position.x == 5 && p.position.y == 6)
  }
}
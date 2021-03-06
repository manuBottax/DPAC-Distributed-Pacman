package client.model.gameElement

import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * @author Manuel Bottazzi
  * @author Margherita Pecorelli
  */
class FruitTest extends FunSuite {

  val apple = Apple("Apple", PointImpl(0,0))
  val cherry = Cherry("Cherry", PointImpl(0,2))
  val grape = Grape("Grapes", PointImpl(1,0))
  val orange = Orange("Orange", PointImpl(1,2))
  val strawberry = Strawberry("Strawberry", PointImpl(1,4))

  test("score"){
    assert(apple.score equals 700)
    assert(cherry.score equals 100)
    assert(grape.score equals 1000)
    assert(orange.score equals 500)
    assert(strawberry.score equals 300)
  }

  test("name"){
    assert(apple.id equals "Apple")
    assert(cherry.id equals "Cherry")
    assert(grape.id equals "Grapes")
    assert(orange.id equals "Orange")
    assert(strawberry.id equals "Strawberry")
  }

  test("position"){
    assert(apple.position equals PointImpl(0,0))
    assert(cherry.position equals PointImpl(0,2))
    assert(grape.position equals PointImpl(1,0))
    assert(orange.position equals PointImpl(1,2))
    assert(strawberry.position equals PointImpl(1,4))
  }

}
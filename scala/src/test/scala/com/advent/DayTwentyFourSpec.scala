package com.advent

import org.scalatest.{FunSuite, Matchers}

class DayTwentyFourSpec extends FunSuite with Matchers {
  test("Part 1 Sample") {
    val components = Set(Component(0,2), Component(2, 2), Component(2, 3), Component(3, 4), Component(3, 5), Component(0, 1), Component(10, 1), Component(9, 10))
    val allBridges = Bridge.all(Bridge(List()), components)
    val scores = allBridges.foldLeft(Map[Bridge, Int]()) { (kv, bridge) => kv + (bridge -> bridge.score) }
    val result = scores.maxBy(_._2)

    assert(result._2 == 31)
  }

  test("Part 1") {
    val components = Set(Component(50, 41), Component(19, 43), Component(17, 50), Component(32, 32), Component(22, 44), Component(9, 39), Component(49, 49), Component(50, 39), Component(49, 10), Component(37, 28), Component(33, 44), Component(14, 14), Component(14, 40), Component(8, 40), Component(10, 25), Component(38, 26), Component(23, 6), Component(4, 16), Component(49, 25), Component(6, 39), Component(0, 50), Component(19, 36), Component(37, 37), Component(42, 26), Component(17, 0), Component(24, 4), Component(0, 36), Component(6, 9), Component(41, 3), Component(13, 3), Component(49, 21), Component(19, 34), Component(16, 46), Component(22, 33), Component(11, 6), Component(22, 26), Component(16, 40), Component(27, 21), Component(31, 46), Component(13, 2), Component(24, 7), Component(37, 45), Component(49, 2), Component(32, 11), Component(3, 10), Component(32, 49), Component(36, 21), Component(47, 47), Component(43, 43), Component(27, 19), Component(14, 22), Component(13, 43), Component(29, 0), Component(33, 36), Component(2, 6))
    val allBridges = Bridge.all(Bridge(List()), components)
    val scores = allBridges.foldLeft(Map[Bridge, Int]()) { (kv, bridge) => kv + (bridge -> bridge.score) }
    val result = scores.maxBy(_._2)

    assert(result._2 == 1859)
  }

  test("Part 2") {
    val components = Set(Component(50, 41), Component(19, 43), Component(17, 50), Component(32, 32), Component(22, 44), Component(9, 39), Component(49, 49), Component(50, 39), Component(49, 10), Component(37, 28), Component(33, 44), Component(14, 14), Component(14, 40), Component(8, 40), Component(10, 25), Component(38, 26), Component(23, 6), Component(4, 16), Component(49, 25), Component(6, 39), Component(0, 50), Component(19, 36), Component(37, 37), Component(42, 26), Component(17, 0), Component(24, 4), Component(0, 36), Component(6, 9), Component(41, 3), Component(13, 3), Component(49, 21), Component(19, 34), Component(16, 46), Component(22, 33), Component(11, 6), Component(22, 26), Component(16, 40), Component(27, 21), Component(31, 46), Component(13, 2), Component(24, 7), Component(37, 45), Component(49, 2), Component(32, 11), Component(3, 10), Component(32, 49), Component(36, 21), Component(47, 47), Component(43, 43), Component(27, 19), Component(14, 22), Component(13, 43), Component(29, 0), Component(33, 36), Component(2, 6))
    val allBridges = Bridge.all(Bridge(List()), components)
    val scores = allBridges.foldLeft(Map[Bridge, Int]()) { (kv, bridge) => kv + (bridge -> bridge.score) }
    val candidates = scores.groupBy(_._1.components.length).maxBy(_._1)
    val result = candidates._2.maxBy(_._2)

    assert(result._2 == 1799)
  }
}

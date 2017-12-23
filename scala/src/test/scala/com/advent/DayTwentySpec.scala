package com.advent

import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwentySpec extends FunSuite with Matchers  {
  def long: Iterator[String] = Source.fromResource("day_20/long.txt").getLines

  test("ParseLine") {
    assert(ParseLine("p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>") ==
      Particle(position = Coordinates(3,0,0), velocity = Coordinates(2,0,0), acceleration = Coordinates(-1,0,0)))
  }

  test("Part 1") {
    assert(DayTwentyPartOne(long) == 119)
  }

  test("Part 2") {
    assert(DayTwentyPartTwo(long) == 471)
  }
}
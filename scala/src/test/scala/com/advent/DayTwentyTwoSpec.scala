package com.advent

import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwentyTwoSpec extends FunSuite with Matchers {
  def long: Seq[String] = Source.fromResource("day_22/long.txt").getLines.toList

  def parse: Board = {
    val board = Board(Map())
    long.zip(12 to -12 by -1).foldLeft[Board](board) {
      case(board, (line, y)) => {
        line.zip(-12 to 12).foldLeft[Board](board) {
          case(board, (char, x)) => if(char == '#') board.infect(XY(x,y)) else board
        }
      }
    }
  }

  test("Part 1 Sample Two Ticks") {
    val board = Board(Map()).infect(XY(-1,0)).infect(XY(1,1))
    val carrier = Carrier(board, XY(0,0), Direction.UP, 0).burst.burst

    assert(carrier.position == XY(-1, 1))
    assert(carrier.board.infected == Set(XY(0,0), XY(1,1)))
  }

  test("Part 1 Sample 70 Ticks") {
    val board = Board(Map()).infect(XY(-1,0)).infect(XY(1,1))
    val carrier = Carrier(board, XY(0,0), Direction.UP, 0).burstMany(70)

    assert(carrier.position == XY(1, 1))
    assert(carrier.infectionsCaused == 41)
  }

  test("Part 1 Sample 10000 Ticks") {
    val board = Board(Map()).infect(XY(-1,0)).infect(XY(1,1))
    val carrier = Carrier(board, XY(0,0), Direction.UP, 0).burstMany(10000)

    assert(carrier.infectionsCaused == 5587)
  }

  test("Part 1 10000 Ticks") {
    val board = parse
    val carrier = Carrier(board, XY(0,0), Direction.UP, 0).burstMany(10000)

    assert(carrier.infectionsCaused == 5587)
  }
}

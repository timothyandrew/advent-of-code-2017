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

  test("Part 2 Sample 100 Ticks") {
    val board = Board(Map()).infect(XY(-1,0)).infect(XY(1,1))
    val carrier = Carrier(board, XY(0,0), Direction.UP, 0).burstMany(100)
    
    assert(carrier.infectionsCaused == 26)
  }

  test("Part 2 Sample 10000000 Ticks") {
    val board = Board(Map()).infect(XY(-1,0)).infect(XY(1,1))
    val carrier = Carrier(board, XY(0,0), Direction.UP, 0).burstMany(10000000)

    assert(carrier.infectionsCaused == 2511944)
  }

  test("Part 2 10000000 Ticks") {
    val board = parse
    val carrier = Carrier(board, XY(0,0), Direction.UP, 0).burstMany(10000000)

    assert(carrier.infectionsCaused == 2512022)
  }
}

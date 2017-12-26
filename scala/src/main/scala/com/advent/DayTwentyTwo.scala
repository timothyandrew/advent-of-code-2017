package com.advent

import com.advent.Direction.Direction
import com.advent.St.St


case class XY(x: Int, y: Int)

case class Board(board: Map[XY, St]) {
  def evolve(xy: XY): Board = {
    val evolved = get(xy) match {
      case St.CLEAN => St.WEAKENED
      case St.WEAKENED => St.INFECTED
      case St.INFECTED => St.FLAGGED
      case St.FLAGGED => St.CLEAN
    }

    Board(board + (xy -> evolved))
  }
  def infect(xy: XY): Board = Board(this.board + (xy -> St.INFECTED))
  def clean(xy: XY): Board = Board(this.board + (xy -> St.CLEAN))
  def get(xy: XY): St = board.getOrElse(xy, St.CLEAN)
}

case class Carrier(board: Board, position: XY, direction: Direction, infectionsCaused: Int) {
  def burst: Carrier = {
    val turn: Direction = board.get(position) match {
      case St.CLEAN => Direction.turnLeft(direction)
      case St.WEAKENED => direction
      case St.INFECTED => Direction.turnRight(direction)
      case St.FLAGGED => Direction.turnBack(direction)
    }

    val evolve = board.evolve(position)

    val move = turn match {
      case Direction.UP => XY(position.x, position.y + 1)
      case Direction.DOWN => XY(position.x, position.y - 1)
      case Direction.LEFT => XY(position.x - 1, position.y)
      case Direction.RIGHT => XY(position.x + 1, position.y)
    }

    val caused = if(evolve.get(position) == St.INFECTED) infectionsCaused + 1 else infectionsCaused

    Carrier(evolve, move, turn, caused)
  }

  def burstMany(bursts: Int): Carrier = {
    (0 until bursts).foldLeft[Carrier](this)((carrier, _) => carrier.burst)
  }
}
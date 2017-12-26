package com.advent

import com.advent.Direction.Direction

case class XY(x: Int, y: Int)

case class Board(board: Map[XY, Boolean]) {
  def toggle(position: XY): Board = if(get(position)) clean(position) else infect(position)
  def infect(xy: XY): Board = Board(this.board + (xy -> true))
  def clean(xy: XY): Board = Board(this.board + (xy -> false))
  def get(xy: XY): Boolean = board.getOrElse(xy, false)

  def infected: Set[XY] = board.foldLeft[Set[XY]](Set()) {
    case(result: Set[XY], (xy: XY, value: Boolean)) => if(value) result + xy else result
  }
}

case class Carrier(board: Board, position: XY, direction: Direction, infectionsCaused: Int) {
  def burst: Carrier = {
    val turn = if(board.get(position)) Direction.turnRight(direction) else Direction.turnLeft(direction)
    val toggle = board.toggle(position)
    val move = turn match {
      case Direction.UP => XY(position.x, position.y + 1)
      case Direction.DOWN => XY(position.x, position.y - 1)
      case Direction.LEFT => XY(position.x - 1, position.y)
      case Direction.RIGHT => XY(position.x + 1, position.y)
    }

    val caused = if(toggle.get(position)) infectionsCaused + 1 else infectionsCaused

    Carrier(toggle, move, turn, caused)
  }

  def burstMany(bursts: Int): Carrier = {
    (0 until bursts).foldLeft[Carrier](this)((carrier, _) => carrier.burst)
  }
}

object DayTwentyTwo {
  def apply(): Unit = {

  }
}
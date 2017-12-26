package com.advent

object Direction extends Enumeration {
  type Direction = Value
  val UP, DOWN, LEFT, RIGHT = Value

  def turnRight(old: Direction): Direction = old match {
    case UP => RIGHT
    case DOWN => LEFT
    case LEFT => UP
    case RIGHT => DOWN
  }

  def turnLeft(old: Direction): Direction = {
    turnRight(turnRight(turnRight(old)))
  }
}
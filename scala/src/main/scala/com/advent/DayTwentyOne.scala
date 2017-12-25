package com.advent

case class Cell(rows: Seq[Seq[Boolean]]) {
  def size: Int = {
    rows.head.length
  }

  def rotateLeft: Cell = {
    val rotated: Seq[Seq[Boolean]] = (size - 1 to 0 by -1).map { j =>
      rows.indices.map { i => rows(i)(j) }
    }

    Cell(rotated)
  }
}

case class Image(rows: Seq[Seq[Cell]])

object Parse {
  def toImage(s: String): Image = {
    val rows: Seq[Seq[Boolean]] = s.trim.split("""\/""").map { partition: String =>
      partition.split("").map(_ == "#").toList
    }.toList

    Image((Cell(rows) :: Nil) :: Nil)
  }
}

object DayTwentyOne {
  def apply(): Unit = {

  }
}
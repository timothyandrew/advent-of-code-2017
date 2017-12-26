package com.advent

import com.advent.Parse.toCell

case class Rule(in: Cell, out: Cell) {
  def isMatch(cell: Cell): Boolean = {
    cell == in || cell == in.rotateLeft || cell == in.rotateLeft.rotateLeft || cell == in.rotateLeft.rotateLeft.rotateLeft ||
      cell == in.flip || cell == in.flip.rotateLeft || cell == in.flip.rotateLeft.rotateLeft || cell == in.flip.rotateLeft.rotateLeft.rotateLeft
  }

  def applyRule(cell: Cell): Cell = {
    if(isMatch(cell)) out else cell
  }
}

case class Cell(rows: Seq[Seq[Boolean]]) {
  def flip: Cell = {
    Cell(rows.map(_.reverse))
  }

  def transform(rules: Seq[Rule]): Cell = {
    val rule = rules.filter(_.isMatch(this)).head
    rule.applyRule(this)
  }

  def size: Int = {
    rows.head.length
  }

  def rotateLeft: Cell = {
    val rotated: Seq[Seq[Boolean]] = (size - 1 to 0 by -1).map { j =>
      rows.indices.map { i => rows(i)(j) }
    }

    Cell(rotated)
  }

  def subdivide2x2: Seq[Seq[Cell]] = {
    rows.grouped(2).map {
      case List(curr: Seq[Boolean], next: Seq[Boolean]) => curr.grouped(2).zip(next.grouped(2)).map {
        case (l: Seq[Boolean], r: Seq[Boolean]) => Cell(l :: r :: Nil)
      }.toList
    }.toList
  }

  def subdivide3x3: Seq[Seq[Cell]] = {
    rows.grouped(3).map {
      case List(x: Seq[Boolean], y: Seq[Boolean], z: Seq[Boolean]) =>
        (0 until (x.length / 3)).map { i =>
          Cell(x.slice(i * 3, (i * 3) + 3) :: y.slice(i * 3, (i * 3) + 3) :: z.slice(i * 3, (i * 3) + 3) :: Nil)
        }
    }.toList
  }
}

object CombineCells {
  def apply(cells: Seq[Seq[Cell]]): Cell = {
    val values: Seq[Seq[Boolean]] = cells.flatMap { row: Seq[Cell] =>
      (0 until row.head.size).map { i =>
        row.flatMap { cell => cell.rows(i) }
      }
    }

    Cell(values)
  }
}

case class Image(cell: Cell, rules: Seq[Rule]) {
  def tick: Image = {
    val transformed: Seq[Seq[Cell]] =
      if(cell.size % 2 == 0) {
        cell.subdivide2x2.map { row =>
          row.map { cell =>
            cell.transform(rules)
          }
        }
      } else {
        cell.subdivide3x3.map { row =>
          row.map { cell =>
            cell.transform(rules)
          }
        }
      }

    val combined = CombineCells(transformed)
    Image(combined, rules)
  }

  def raw: Seq[Boolean] = {
    cell.rows.flatten
  }

  def tickMany(ticks: Int): Image = {
    (0 until ticks).foldLeft[Image](this)((image: Image, _: Int) => image.tick)
  }

  def onPixels: Int = {
    this.raw.count(_ == true)
  }
}

object Parse {
  def toCell(s: String): Cell = {
    val rows: Seq[Seq[Boolean]] = s.trim.split("""\/""").map { partition: String =>
      partition.split("").map(_ == "#").toList
    }.toList

    Cell(rows)
  }

  def toImage(s: String, rules: Seq[Rule]): Image = {
    Image(toCell(s), rules)
  }

  def toRule(s: String): Rule = {
    s.split("""=>""") match {
      case Array(inS: String, outS: String) =>
        Rule(toCell(inS), toCell(outS))
    }
  }
}

object DayTwentyOne {
  def apply(): Unit = {

  }
}
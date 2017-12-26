package com.advent


case class Rule(in: Cell, out: Cell)

case class Cell(rows: Seq[Seq[Boolean]]) {
  def flip: Cell = {
    Cell(rows.map(_.reverse))
  }

  def transform(rules: Map[Cell, Rule]): Cell = {
    rules(this).out
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
      case Vector(curr: Seq[Boolean], next: Seq[Boolean]) => curr.grouped(2).zip(next.grouped(2)).map {
        case (l: Seq[Boolean], r: Seq[Boolean]) => Cell(l :: r :: Nil)
      }.toVector
    }.toVector
  }

  def subdivide3x3: Seq[Seq[Cell]] = {
    rows.grouped(3).map {
      case Vector(x: Seq[Boolean], y: Seq[Boolean], z: Seq[Boolean]) =>
        (0 until (x.length / 3)).map { i =>
          Cell(x.slice(i * 3, (i * 3) + 3) :: y.slice(i * 3, (i * 3) + 3) :: z.slice(i * 3, (i * 3) + 3) :: Nil)
        }
    }.toVector
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

case class Image(cell: Cell, rules: Map[Cell, Rule]) {
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
      partition.split("").map(_ == "#").toVector
    }.toVector

    Cell(rows)
  }

  def toImage(s: String, rules: Seq[Rule]): Image = {
    val rulesMap = rules.foldLeft[Map[Cell, Rule]](Map())((rulesMap: Map[Cell, Rule], rule: Rule) => {
      val cell = rule.in
      val cellCombos = cell :: cell.rotateLeft :: cell.rotateLeft.rotateLeft :: cell.rotateLeft.rotateLeft.rotateLeft :: cell.flip :: cell.flip.rotateLeft :: cell.flip.rotateLeft.rotateLeft :: cell.flip.rotateLeft.rotateLeft.rotateLeft :: Nil
      cellCombos.foldLeft(rulesMap)((rulesMap, cell) => rulesMap + (cell -> rule))
    })

    Image(toCell(s), rulesMap)
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
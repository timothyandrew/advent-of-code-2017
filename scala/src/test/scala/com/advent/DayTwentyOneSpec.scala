package com.advent

import org.scalatest.{FunSuite, Matchers}

class DayTwentyOneSpec extends FunSuite with Matchers  {
  test("Parse image") {
    val image: Image = Parse.toImage(" ##./#../... ", List())
    val cell: Cell = image.cell

    assert(cell == Cell(List(true, true, false) :: List(true, false, false) :: List(false, false, false) :: Nil))
  }

  test("Result Part 1 Short") {
    val rules = Parse.toRule(".#./..#/### => #..#/..../..../#..#") :: Parse.toRule("../.# => ##./#../...") :: Nil
    val image: Image = Parse.toImage(".#./..#/###", rules)

    assert(image.tickMany(2).onPixels == 12)
  }

  test("Result Part 1 Long") {
    val rules = Parse.toRule("../.. => .../.../###") :: Parse.toRule("#./.. => .../.#./.##") :: Parse.toRule("##/.. => .#./.#./...") :: Parse.toRule(".#/#. => ###/..#/.##") :: Parse.toRule("##/#. => ..#/###/#..") :: Parse.toRule("##/## => ..#/#../##.") :: Parse.toRule(".../.../... => .##./##../..##/.##.") :: Parse.toRule("#../.../... => ##../.#.#/..#./###.") :: Parse.toRule(".#./.../... => ##.#/#.#./.#../..##") :: Parse.toRule("##./.../... => ...#/##.#/.#.#/#.##") :: Parse.toRule("#.#/.../... => ..#./#.../###./...#") :: Parse.toRule("###/.../... => #.#./...#/#.#./###.") :: Parse.toRule(".#./#../... => ...#/###./.##./...#") :: Parse.toRule("##./#../... => ###./####/###./..##") :: Parse.toRule("..#/#../... => ####/#.../####/#.##") :: Parse.toRule("#.#/#../... => #.##/.#.#/##.#/###.") :: Parse.toRule(".##/#../... => ..../.#../.#.#/.##.") :: Parse.toRule("###/#../... => ..##/##.#/..##/.###") :: Parse.toRule(".../.#./... => ###./..##/.#../#..#") :: Parse.toRule("#../.#./... => ###./.#../#.../#...") :: Parse.toRule(".#./.#./... => ####/..#./.##./##..") :: Parse.toRule("##./.#./... => .#../#.#./###./###.") :: Parse.toRule("#.#/.#./... => ####/.##./##.#/.###") :: Parse.toRule("###/.#./... => #.#./..##/.##./#...") :: Parse.toRule(".#./##./... => ####/#.##/####/..#.") :: Parse.toRule("##./##./... => #.../.#../..../#.##") :: Parse.toRule("..#/##./... => #..#/..##/#.../####") :: Parse.toRule("#.#/##./... => ###./##../..##/#...") :: Parse.toRule(".##/##./... => ..../#.##/.###/#.#.") :: Parse.toRule("###/##./... => .#../##.#/.#../##..") :: Parse.toRule(".../#.#/... => ...#/.###/.##./###.") :: Parse.toRule("#../#.#/... => ###./##../#.#./.##.") :: Parse.toRule(".#./#.#/... => ..#./.#../.##./.###") :: Parse.toRule("##./#.#/... => #.../#.../.##./.#..") :: Parse.toRule("#.#/#.#/... => .##./..##/.###/#...") :: Parse.toRule("###/#.#/... => ..../####/###./....") :: Parse.toRule(".../###/... => #.##/.#.#/#.##/...#") :: Parse.toRule("#../###/... => #.../#.#./.#../#...") :: Parse.toRule(".#./###/... => ...#/###./.##./.#.#") :: Parse.toRule("##./###/... => ##../####/###./#.##") :: Parse.toRule("#.#/###/... => ...#/###./##.#/.#.#") :: Parse.toRule("###/###/... => #.#./##.#/..../.##.") :: Parse.toRule("..#/.../#.. => ...#/..#./..#./##..") :: Parse.toRule("#.#/.../#.. => ..#./#.##/#.#./#.##") :: Parse.toRule(".##/.../#.. => ####/####/#.##/#...") :: Parse.toRule("###/.../#.. => ###./..#./###./.#..") :: Parse.toRule(".##/#../#.. => ...#/####/..../###.") :: Parse.toRule("###/#../#.. => ##.#/.#../##.#/...#") :: Parse.toRule("..#/.#./#.. => ###./#.##/...#/##..") :: Parse.toRule("#.#/.#./#.. => #.../..#./..#./#.##") :: Parse.toRule(".##/.#./#.. => ##.#/...#/#.#./.###") :: Parse.toRule("###/.#./#.. => .#../..##/#.#./..#.") :: Parse.toRule(".##/##./#.. => #.../#.#./.###/#...") :: Parse.toRule("###/##./#.. => .##./.#../.#.#/.###") :: Parse.toRule("#../..#/#.. => ###./#..#/#.../##.#") :: Parse.toRule(".#./..#/#.. => #.#./#..#/#.../.###") :: Parse.toRule("##./..#/#.. => ...#/..##/..#./####") :: Parse.toRule("#.#/..#/#.. => ####/#..#/###./#.#.") :: Parse.toRule(".##/..#/#.. => ..#./..#./..../.##.") :: Parse.toRule("###/..#/#.. => ...#/#..#/#.#./....") :: Parse.toRule("#../#.#/#.. => ..##/.#.#/.###/.##.") :: Parse.toRule(".#./#.#/#.. => ..../##.#/..##/#..#") :: Parse.toRule("##./#.#/#.. => ..#./..##/#..#/#..#") :: Parse.toRule("..#/#.#/#.. => ..#./#.../#.#./##..") :: Parse.toRule("#.#/#.#/#.. => ##.#/..##/.###/...#") :: Parse.toRule(".##/#.#/#.. => #.##/.##./##../#.#.") :: Parse.toRule("###/#.#/#.. => ####/##.#/#..#/#.#.") :: Parse.toRule("#../.##/#.. => ..##/#.#./####/####") :: Parse.toRule(".#./.##/#.. => ##../###./####/....") :: Parse.toRule("##./.##/#.. => .###/####/..#./...#") :: Parse.toRule("#.#/.##/#.. => ###./##../##../#.##") :: Parse.toRule(".##/.##/#.. => ##../.###/####/.#.#") :: Parse.toRule("###/.##/#.. => ##../.##./#.../..#.") :: Parse.toRule("#../###/#.. => #.#./.#.#/#.../....") :: Parse.toRule(".#./###/#.. => .##./##../...#/##..") :: Parse.toRule("##./###/#.. => #.#./..../.##./##.#") :: Parse.toRule("..#/###/#.. => ...#/...#/##.#/...#") :: Parse.toRule("#.#/###/#.. => .##./.###/#..#/.##.") :: Parse.toRule(".##/###/#.. => ####/..##/#.../####") :: Parse.toRule("###/###/#.. => ...#/####/..#./.###") :: Parse.toRule(".#./#.#/.#. => .##./#.##/.##./.###") :: Parse.toRule("##./#.#/.#. => ..##/.#../##.#/###.") :: Parse.toRule("#.#/#.#/.#. => .#../..../.#.#/#...") :: Parse.toRule("###/#.#/.#. => ###./..#./..../#.#.") :: Parse.toRule(".#./###/.#. => #..#/.#../#.../..##") :: Parse.toRule("##./###/.#. => .##./...#/.###/....") :: Parse.toRule("#.#/###/.#. => .###/###./#.#./.#.#") :: Parse.toRule("###/###/.#. => #.##/.#.#/#.#./.##.") :: Parse.toRule("#.#/..#/##. => .###/..../####/####") :: Parse.toRule("###/..#/##. => #.##/###./..##/.##.") :: Parse.toRule(".##/#.#/##. => ..../...#/#..#/..##") :: Parse.toRule("###/#.#/##. => #.##/.#../.#../....") :: Parse.toRule("#.#/.##/##. => ..##/..##/#.../#..#") :: Parse.toRule("###/.##/##. => ##.#/#.../#.##/..##") :: Parse.toRule(".##/###/##. => ...#/..#./##../#.##") :: Parse.toRule("###/###/##. => #.##/#..#/..#./...#") :: Parse.toRule("#.#/.../#.# => ##.#/.#../##.#/.##.") :: Parse.toRule("###/.../#.# => #.#./..##/.#.#/##.#") :: Parse.toRule("###/#../#.# => ..#./#.##/...#/.###") :: Parse.toRule("#.#/.#./#.# => .###/#.##/#..#/#.##") :: Parse.toRule("###/.#./#.# => ..../..#./###./..#.") :: Parse.toRule("###/##./#.# => .###/##../..##/####") :: Parse.toRule("#.#/#.#/#.# => #.#./####/.#../.##.") :: Parse.toRule("###/#.#/#.# => ####/..../..##/#...") :: Parse.toRule("#.#/###/#.# => #.../.##./#.../...#") :: Parse.toRule("###/###/#.# => .#.#/...#/..../..##") :: Parse.toRule("###/#.#/### => .#../#.##/#.##/.###") :: Parse.toRule("###/###/### => #.../.#.#/#..#/#.##") :: Nil
    val image: Image = Parse.toImage(".#./..#/###", rules)

    assert(image.tick.tick.tick.tick.tick.onPixels == 125)
  }
}
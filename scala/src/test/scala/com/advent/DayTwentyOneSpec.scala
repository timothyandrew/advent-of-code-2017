package com.advent

import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwentyOneSpec extends FunSuite with Matchers  {
  //def long: Iterator[String] = Source.fromResource("day_21/long.txt").getLines

  test("Parse image") {
    val image: Image = Parse.toImage(" ##./#../... ")
    val cell: Cell = image.rows.head.head

    assert(cell == Cell(List(true, true, false) :: List(true, false, false) :: List(false, false, false) :: Nil))
  }

//  test("Transform enhancement rule") {
//    val enhancementRule = Parse.toEnhancementRule("../.# => ##./#../...")
//
//    assert(enhancementRule.transform(Parse.toImage("#./..")) == Parse.toImage("##./#../..."))
//    assert(enhancementRule.transform(Parse.toImage(".#/..")) == Parse.toImage("##./#../..."))
//    assert(enhancementRule.transform(Parse.toImage("../#.")) == Parse.toImage("##./#../..."))
//    assert(enhancementRule.transform(Parse.toImage("../.#")) == Parse.toImage("##./#../..."))
//    assert(enhancementRule.transform(Parse.toImage("../..")) == Parse.toImage("../.."))
//  }
}
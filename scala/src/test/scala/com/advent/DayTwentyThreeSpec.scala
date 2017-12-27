package com.advent

import org.scalatest.{FunSuite, Matchers}

class DayTwentyThreeSpec extends FunSuite with Matchers {
  test("Part 1") {
    val instructions = Instruction("set", "b", "57") :: Instruction("set", "c", "b") :: Instruction("jnz", "a", "2") :: Instruction("jnz", "1", "5") :: Instruction("mul", "b", "100") :: Instruction("sub", "b", "-100000") :: Instruction("set", "c", "b") :: Instruction("sub", "c", "-17000") :: Instruction("set", "f", "1") :: Instruction("set", "d", "2") :: Instruction("set", "e", "2") :: Instruction("set", "g", "d") :: Instruction("mul", "g", "e") :: Instruction("sub", "g", "b") :: Instruction("jnz", "g", "2") :: Instruction("set", "f", "0") :: Instruction("sub", "e", "-1") :: Instruction("set", "g", "e") :: Instruction("sub", "g", "b") :: Instruction("jnz", "g", "-8") :: Instruction("sub", "d", "-1") :: Instruction("set", "g", "d") :: Instruction("sub", "g", "b") :: Instruction("jnz", "g", "-13") :: Instruction("jnz", "f", "2") :: Instruction("sub", "h", "-1") :: Instruction("set", "g", "b") :: Instruction("sub", "g", "c") :: Instruction("jnz", "g", "2") :: Instruction("jnz", "1", "3") :: Instruction("sub", "b", "-17") :: Instruction("jnz", "1", "-23") :: Nil
    val coprocessor = TickUntilDone(Coprocessor(Map(), instructions, 0, 0, done = false))

    assert(coprocessor.mulCount == 3025)
  }

  test("Part 2: Find primes") {
    assert(PrimeTest() == 915)
  }
}

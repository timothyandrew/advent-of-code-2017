package com.advent

case class State(particles: List[Particle])
case class Coordinates(x: Long, y: Long, z: Long)
case class Particle(position: Coordinates, velocity: Coordinates, acceleration: Coordinates)

object ManhattanDistanceToZero {
  def apply(particle: Particle): Long = {
    Math.abs(particle.position.x) + Math.abs(particle.position.y) + Math.abs(particle.position.z)
  }
}

object FindClosestToZero {
  def apply(state: State): Int = {
    state.particles.indexOf(state.particles.minBy(ManhattanDistanceToZero(_)))
  }
}

object Tick {
  def tickParticle(particle: Particle): Particle = {
    val position = particle.position
    val velocity = particle.velocity
    val acc = particle.acceleration
    val updatedVelocity = Coordinates(velocity.x + acc.x, velocity.y + acc.y, velocity.z + acc.z)

    particle.copy(
      position = Coordinates(position.x + updatedVelocity.x, position.y + updatedVelocity.y, position.z + updatedVelocity.z),
      velocity = updatedVelocity
    )
  }

  def apply(state: State): State = {
    State(state.particles.map(tickParticle))
  }
}

object ParseLine {
  def apply(line: String): Particle = {
    val Pattern = """p=\<\s?(\-?\d+),(\-?\d+),(\-?\d+)>, v=\<\s?(\-?\d+),(\-?\d+),(\-?\d+)>, a=\<\s?(\-?\d+),(\-?\d+),(\-?\d+)>""".r
    line match {
      case Pattern(px, py, pz, vx, vy, vz, ax, ay, az: String) =>
        Particle(position = Coordinates(px.toLong, py.toLong, pz.toLong), acceleration = Coordinates(ax.toLong, ay.toLong, az.toLong), velocity = Coordinates(vx.toLong, vy.toLong, vz.toLong))
    }
  }
}

object RemoveCollisions {
  def apply(state: State): State = {
    val particlesByPosition: Map[Coordinates, List[Particle]] = state.particles.groupBy[Coordinates](_.position)

    val withoutCollisions = particlesByPosition.mapValues[List[Particle]] { particles =>
      if(particles.lengthCompare(1) > 0) {
        List()
      } else {
        particles
      }
    }

    State(withoutCollisions.values.flatten.toList)
  }
}

object DayTwentyPartOne {
  def apply(input: Iterator[String]): Int = {
    val particles = input.map(ParseLine(_)).toList
    val state = State(particles)

    val r = 1 to 1000
    val computed = r.foldLeft[State](state)((state, _) => Tick(state))

    FindClosestToZero(computed)
  }
}

object DayTwentyPartTwo {
  def apply(input: Iterator[String]): Int = {
    val particles = input.map(ParseLine(_)).toList
    val state = State(particles)

    val r = 1 to 1000
    val computed = r.foldLeft[State](state)((state, _) => RemoveCollisions(Tick(state)))

    computed.particles.length
  }
}

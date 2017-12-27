package com.advent

case class Component(left: Int, right: Int, taken: Option[String] = None) {
  def willFitInto(base: Component): Boolean = {
    base.taken.get match {
      case "left" => base.right == left || base.right == right
      case "right" => base.left == left || base.left == right
    }
  }

  def unTakenValue: Int = {
    taken.get match {
      case "left" => right
      case "right" => left
    }
  }

}

case class Bridge(components: Seq[Component]) {
  def add(c: Component): Bridge = {
    val component =
      if(components.isEmpty) {
        if(c.left == 0)  c.copy(taken = Some("left")) else c.copy(taken = Some("right"))
      } else {
        if(c.left == components.head.unTakenValue) c.copy(taken = Some("left")) else c.copy(taken = Some("right"))
      }
    Bridge(component +: components)
  }

  def score: Int = {
    components.foldLeft(0)((score, c) =>
      score + c.left + c.right
    )
  }
}

object Bridge {
  def all(bridge: Bridge, components: Set[Component]): Set[Bridge] = {
    if(bridge.components.isEmpty) {
      components.filter(c => c.left == 0 || c.right == 0).foldLeft(Set[Bridge]())((bridges, c) =>
        bridges ++ Bridge.all(bridge.add(c), components - c)
      )
    }
    else if(components.isEmpty || components.forall(!_.willFitInto(bridge.components.head))) {
      Set(bridge)
    } else {
      components.filter(_.willFitInto(bridge.components.head)).foldLeft(Set[Bridge]())((bridges, c) =>
        bridges ++ Bridge.all(bridge.add(c), components - c)
      )
    }
  }
}

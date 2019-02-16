package br.vedovato.instructionset
import br.vedovato.model.Directions._
import br.vedovato.model.{ Coordinate, Direction }

trait MoveInstructionSet {
  def applyMovement(coordinate: Coordinate, facingDirection: Direction): Coordinate =
    (coordinate, facingDirection) match {
      case (c, North) => c.copy(y = c.y + 1)
      case (c, South) => c.copy(y = c.y - 1)
      case (c, East) => c.copy(x = c.x + 1)
      case (c, West) => c.copy(x = c.x - 1)
    }
}

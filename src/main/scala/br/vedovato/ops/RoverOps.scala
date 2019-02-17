package br.vedovato.ops

import br.vedovato.instructionset.{ MoveInstructionSet, SurfaceInstructionSet, TurnInstructionSet }
import br.vedovato.model.Rover

trait RoverOps {
  def self: Rover

  case object InstructionSet extends TurnInstructionSet with MoveInstructionSet with SurfaceInstructionSet

  def turnLeft: Rover = self.copy(facingDirection = InstructionSet.leftTurn(self.facingDirection))
  def turnRight: Rover = self.copy(facingDirection = InstructionSet.rightTurn(self.facingDirection))

  def safeMove: Either[CommandFeedback, Rover] = {
    val newRoverCoordinate = InstructionSet.applyMovement(self.position, self.facingDirection)

    if (InstructionSet.isCoordinateInsideBoundaries(newRoverCoordinate, self.explorationSurfaceEdge)) {
      Right(self.copy(position = newRoverCoordinate))
    } else {
      Left(RoverOutsideOfBounds)
    }
  }

  def unsafeMove: Rover = safeMove.fold(_ => self, r => r)
}

package br.vedovato.command

import br.vedovato.command.SurfaceCommands._
import br.vedovato.instructionset.{ MoveInstructionSet, TurnInstructionSet }
import br.vedovato.model.Rover

object RoverCommands extends MoveInstructionSet with TurnInstructionSet {
  implicit class RoverMethods(val rover: Rover) extends AnyVal {
    def turnLeft: Rover = rover.copy(facingDirection = leftTurn(rover.facingDirection))
    def turnRight: Rover = rover.copy(facingDirection = rightTurn(rover.facingDirection))

    def safeMove: Either[CommandFeedback, Rover] = {
      val newRoverCoordinate = applyMovement(rover.position, rover.facingDirection)

      if (rover.surface.isValidSurfaceCoordinate(newRoverCoordinate)) {
        Right(rover.copy(position = newRoverCoordinate))
      } else {
        Left(RoverOutsideOfBounds)
      }
    }

    def unsafeMove: Rover = {
      val newRoverCoordinate = applyMovement(rover.position, rover.facingDirection)

      if (rover.surface.isValidSurfaceCoordinate(newRoverCoordinate)) {
        rover.copy(position = newRoverCoordinate)
      } else {
        rover
      }
    }
  }
}

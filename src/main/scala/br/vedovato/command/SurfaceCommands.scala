package br.vedovato.command

import br.vedovato.instructionset.SurfaceInstructionSet
import br.vedovato.model.{ Coordinate, Surface }

object SurfaceCommands extends SurfaceInstructionSet {
  implicit class SurfaceMethods(val surface: Surface) extends AnyVal {
    def isValidSurfaceCoordinate(coordinate: Coordinate): Boolean = {
      isCoordinateInsideBoundaries(coordinate, surface.edgeCoordinates)
    }
  }
}

package br.vedovato.instructionset
import br.vedovato.model.Coordinate

trait SurfaceInstructionSet {
  def isCoordinateInsideBoundaries(coordinate: Coordinate, edge: Coordinate): Boolean = {
    (coordinate.x >= 0 && coordinate.x <= edge.x) && (coordinate.y >= 0 && coordinate.y <= edge.y)
  }
}

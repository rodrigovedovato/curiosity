package br.vedovato
import br.vedovato.instructionset.SurfaceInstructionSet
import br.vedovato.model.Coordinate
import org.scalatest.FlatSpec

class SurfaceInstructionSetSpec extends FlatSpec {
  case object SurfaceInstructionSetMock extends SurfaceInstructionSet

  val edgeCoordinate = Coordinate(5, 5)

  "SurfaceInstructionSet" should "return true for valid coordinate" in {
    assert(SurfaceInstructionSetMock.isCoordinateInsideBoundaries(Coordinate(1, 1), edgeCoordinate))
  }

  "SurfaceInstructionSet" should "return false for coordinate with negative value" in {
    assert(SurfaceInstructionSetMock.isCoordinateInsideBoundaries(Coordinate(1, -1), edgeCoordinate) == false)
  }

  "SurfaceInstructionSet" should "return false for coordinate outside of bounds" in {
    assert(SurfaceInstructionSetMock.isCoordinateInsideBoundaries(Coordinate(6, 1), edgeCoordinate) == false)
  }
}

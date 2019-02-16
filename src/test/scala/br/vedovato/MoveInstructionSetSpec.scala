package br.vedovato
import br.vedovato.instructionset.MoveInstructionSet
import br.vedovato.model.{ Coordinate, Directions }
import org.scalatest.FlatSpec

class MoveInstructionSetSpec extends FlatSpec {
  case object MoveInstructionSpecMock extends MoveInstructionSet

  "MoveInstructionSet" should "increment y axis when facing North" in {
    assertResult(Coordinate(0, 1))(MoveInstructionSpecMock.applyMovement(Coordinate(0, 0), Directions.North))
  }

  "MoveInstructionSet" should "decrement y axis when facing South" in {
    assertResult(Coordinate(0, 0))(MoveInstructionSpecMock.applyMovement(Coordinate(0, 1), Directions.South))
  }

  "MoveInstructionSet" should "increment x axis when facing East" in {
    assertResult(Coordinate(1, 0))(MoveInstructionSpecMock.applyMovement(Coordinate(0, 0), Directions.East))
  }

  "MoveInstructionSet" should "decrement x axis when facing West" in {
    assertResult(Coordinate(0, 0))(MoveInstructionSpecMock.applyMovement(Coordinate(1, 0), Directions.West))
  }
}

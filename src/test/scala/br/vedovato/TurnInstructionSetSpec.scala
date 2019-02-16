package br.vedovato

import br.vedovato.instructionset.TurnInstructionSet
import br.vedovato.model.Directions
import org.scalatest.FlatSpec

class TurnInstructionSetSpec extends FlatSpec {
  case object TurnInstructionsMock extends TurnInstructionSet

  "TurnInstructionSet" should "return West after turning left from North" in {
    assertResult(Directions.West)(TurnInstructionsMock.leftTurn(Directions.North))
  }

  "TurnInstructionSet" should "return East after turning left from South" in {
    assertResult(Directions.East)(TurnInstructionsMock.leftTurn(Directions.South))
  }

  "TurnInstructionSet" should "return North after turning left from East" in {
    assertResult(Directions.North)(TurnInstructionsMock.leftTurn(Directions.East))
  }

  "TurnInstructionSet" should "return South after turning left from West" in {
    assertResult(Directions.South)(TurnInstructionsMock.leftTurn(Directions.West))
  }

  "TurnInstructionSet" should "return East after turning right from North" in {
    assertResult(Directions.East)(TurnInstructionsMock.rightTurn(Directions.North))
  }

  "TurnInstructionSet" should "return West after turning right from South" in {
    assertResult(Directions.West)(TurnInstructionsMock.rightTurn(Directions.South))
  }

  "TurnInstructionSet" should "return South after turning right from East" in {
    assertResult(Directions.South)(TurnInstructionsMock.rightTurn(Directions.East))
  }

  "TurnInstructionSet" should "return North after turning right from west" in {
    assertResult(Directions.North)(TurnInstructionsMock.rightTurn(Directions.West))
  }
}

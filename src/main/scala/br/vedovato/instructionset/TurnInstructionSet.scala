package br.vedovato.instructionset

import br.vedovato.model.Direction
import br.vedovato.model.Directions._

trait TurnInstructionSet {
  def leftTurn(from: Direction): Direction = from match {
    case North => West
    case South => East
    case East => North
    case West => South
  }

  def rightTurn(from: Direction): Direction = from match {
    case North => East
    case South => West
    case East => South
    case West => North
  }
}

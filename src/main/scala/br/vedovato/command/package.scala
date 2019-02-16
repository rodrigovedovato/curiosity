package br.vedovato

package object command {
  sealed trait CommandFeedback
  case object RoverOutsideOfBounds extends CommandFeedback
}

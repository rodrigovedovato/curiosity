package br.vedovato

package object modules {
  trait RoverErrors

  case object PositionAlreadyTaken extends RoverErrors
}

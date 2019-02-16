package br.vedovato.model

case class Rover(
  name: String,
  id: RoverId,
  position: Coordinate,
  facingDirection: Direction,
  surface: Surface)

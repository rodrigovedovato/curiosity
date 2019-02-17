package br.vedovato.model

case class Rover(
  name: Option[String],
  position: Coordinate,
  facingDirection: Direction,
  explorationSurfaceEdge: Coordinate)

case class RoverLandingResponse(id: RoverId, name: String, landingTime: Long, roverActions: Map[String, String])
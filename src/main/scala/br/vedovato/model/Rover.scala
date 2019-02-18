package br.vedovato.model

case class Rover(
  id: Option[String],
  name: Option[String],
  position: Coordinate,
  facingDirection: Direction,
  explorationSurfaceEdge: Coordinate)

case class RoverLandingResponse(id: RoverId, name: String, landingTime: Long, roverActions: Map[String, String])
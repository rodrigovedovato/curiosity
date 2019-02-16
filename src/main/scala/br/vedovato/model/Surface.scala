package br.vedovato.model

case class Surface(
  edgeCoordinates: Coordinate,
  occupiedPositions: Map[Coordinate, RoverId])

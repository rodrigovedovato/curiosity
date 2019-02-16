package br.vedovato
import br.vedovato.model._
import br.vedovato.command.RoverCommands._

object Sample extends App {
  val sampleRover = Rover(
    name = "Curiosity",
    id = 42,
    position = Coordinate(1, 2),
    facingDirection = Directions.North,
    surface = Surface(Coordinate(5, 5), Map[Coordinate, RoverId]()))

  var finalRover = sampleRover
    .turnLeft.unsafeMove
    .turnLeft.unsafeMove
    .turnLeft.unsafeMove
    .turnLeft.unsafeMove.unsafeMove

  println(s"${finalRover.position.x} ${finalRover.position.y} ${finalRover.facingDirection}")
}

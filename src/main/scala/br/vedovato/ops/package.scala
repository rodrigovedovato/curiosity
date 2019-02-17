package br.vedovato
import br.vedovato.model.Rover

package object ops {
  sealed trait CommandFeedback
  case object RoverOutsideOfBounds extends CommandFeedback

  implicit def toRoverOps(r: Rover): RoverOps = new RoverOps {
    override def self: Rover = r
  }
}

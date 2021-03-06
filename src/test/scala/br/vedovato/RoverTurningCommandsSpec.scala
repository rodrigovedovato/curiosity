package br.vedovato
import org.scalatest.FunSpec
import br.vedovato.ops._
import br.vedovato.model._

class RoverTurningCommandsSpec extends FunSpec {
  val northFacingRover = Rover(
    id = Some("42"),
    name = Some("Unit Test Rover"),
    position = Coordinate(0, 0),
    facingDirection = Directions.North,
    explorationSurfaceEdge = Coordinate(5, 5))

  val southFacingRover = Rover(
    id = Some("42"),
    name = Some("Unit Test Rover"),
    position = Coordinate(0, 0),
    facingDirection = Directions.South,
    explorationSurfaceEdge = Coordinate(5, 5))

  describe("A rover") {
    describe("when facing north") {
      it("should be facing East after turning it right 1 time") {
        assertResult(Directions.East)(northFacingRover.turnRight.facingDirection)
      }
      it("should be facing South after turning it right 2 times") {
        assertResult(Directions.South)(northFacingRover.turnRight.turnRight.facingDirection)
      }
      it("should be facing North after turning right 4 times") {
        assertResult(Directions.North)(northFacingRover.turnRight.turnRight.turnRight.turnRight.facingDirection)
      }
      it("should be facing West after turning it left 1 time") {
        assertResult(Directions.West)(northFacingRover.turnLeft.facingDirection)
      }
      it("should be facing South after turning it left 2 times") {
        assertResult(Directions.South)(northFacingRover.turnLeft.turnLeft.facingDirection)
      }
      it("should be facing North after turning left 4 times") {
        assertResult(Directions.North)(northFacingRover.turnLeft.turnLeft.turnLeft.turnLeft.facingDirection)
      }
    }
    describe("When facing south") {
      it("should be facing West after turning it right 1 time") {
        assertResult(Directions.West)(southFacingRover.turnRight.facingDirection)
      }
      it("should be facing North after turning it right 2 times") {
        assertResult(Directions.North)(southFacingRover.turnRight.turnRight.facingDirection)
      }
      it("should be facing South after turning right 4 times") {
        assertResult(Directions.South)(southFacingRover.turnRight.turnRight.turnRight.turnRight.facingDirection)
      }
      it("should be facing East after turning it left 1 time") {
        assertResult(Directions.East)(southFacingRover.turnLeft.facingDirection)
      }
      it("should be facing North after turning it left 2 times") {
        assertResult(Directions.North)(southFacingRover.turnLeft.turnLeft.facingDirection)
      }
      it("should be facing South after turning left 4 times") {
        assertResult(Directions.South)(southFacingRover.turnLeft.turnLeft.turnLeft.turnLeft.facingDirection)
      }
    }
  }
}
package br.vedovato
import br.vedovato.model._
import br.vedovato.ops._
import org.scalatest.FunSpec

class RoverMovementCommandSpec extends FunSpec {

  val northFacingRover = Rover(
    name = Some("Unit Test Rover"),
    position = Coordinate(0, 0),
    facingDirection = Directions.North,
    explorationSurfaceEdge = Coordinate(5, 5))

  val southFacingRover: Rover =
    northFacingRover.copy(facingDirection = Directions.South, position = Coordinate(5, 5))

  val westFacingRover: Rover =
    northFacingRover.copy(facingDirection = Directions.West, position = Coordinate(5, 5))

  val eastFacingRover: Rover = northFacingRover.copy(facingDirection = Directions.East)

  describe("A rover") {
    describe("when facing north from (0,0)") {
      it("Should be out of bounds after 6 consecutive move calls") {
        val result = for {
          r <- northFacingRover.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
        } yield r

        assert(result.isLeft)
      }
    }
    describe("when facing south from (5,5)") {
      it("Should be out of bounds after 6 consecutive move calls") {
        val result = for {
          r <- southFacingRover.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
        } yield r

        assert(result.isLeft)
      }
    }
    describe("when facing west from (5,5)") {
      it("Should be out of bounds after 6 consecutive move calls") {
        val result = for {
          r <- westFacingRover.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
        } yield r

        assert(result.isLeft)
      }
    }
    describe("when facing east from (0,0)") {
      it("Should be out of bounds after 6 consecutive move calls") {
        val result = for {
          r <- eastFacingRover.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
          r <- r.safeMove
        } yield r

        assert(result.isLeft)
      }
    }
  }
}

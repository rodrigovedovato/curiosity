package br.vedovato.endpoints

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.Location
import akka.http.scaladsl.server.Route
import br.vedovato.model.Rover
import br.vedovato.modules.RoverModule
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import br.vedovato.model.Directions.{ circeDecoder, circeEncoder }

import scala.util.{ Failure, Success }

trait RoverEndpoints extends FailFastCirceSupport {
  def roverModule: RoverModule

  private def landRover(rover: Rover) =
    onComplete(roverModule.landRover(rover)) {
      case Success(value) =>
        respondWithHeader(Location(s"/rover/${value.id}")) {
          complete(StatusCodes.Created -> value)
        }
      case Failure(_) => complete(StatusCodes.InternalServerError)
    }

  private def getRover(roverId: String) =
    onComplete(roverModule.getRover(roverId)) {
      case Success(value) =>
        rejectEmptyResponse {
          complete(value)
        }
      case Failure(_) => complete(StatusCodes.InternalServerError)
    }

  private def getAllRovers() = {
    onComplete(roverModule.getAllRovers) {
      case Success(rovers) => complete(rovers)
      case Failure(_) => complete(StatusCodes.InternalServerError)
    }
  }

  private def moveRover(roverId: String) =
    onComplete(roverModule.move(roverId)) {
      case Success(commandFeeback) =>
        rejectEmptyResponse {
          commandFeeback match {
            case Some(cf) =>
              cf match {
                case Left(_) => complete(StatusCodes.UnprocessableEntity)
                case Right(rover) => complete(rover.position)
              }
            case None => complete(commandFeeback)
          }
        }
      case Failure(_) => complete(StatusCodes.InternalServerError)
    }

  private def turnRoverLeft(roverId: String) =
    onComplete(roverModule.turnLeft(roverId)) {
      case Success(rover) =>
        rejectEmptyResponse {
          complete(StatusCodes.OK -> rover)
        }
      case Failure(_) => complete(StatusCodes.InternalServerError)
    }

  private def turnRoverRight(roverId: String) =
    onComplete(roverModule.turnRight(roverId)) {
      case Success(rover) =>
        rejectEmptyResponse {
          complete(StatusCodes.OK -> rover)
        }
      case Failure(_) => complete(StatusCodes.InternalServerError)
    }

  lazy val roverRoutes: Route =
    path("rovers") {
      get {
        getAllRovers()
      } ~
        post {
          entity(as[Rover]) { rover =>
            landRover(rover)
          }
        }
    } ~
      pathPrefix("rover") {
        path(Segment) { roverId =>
          get {
            getRover(roverId)
          }
        } ~
          path(Segment / "move") { roverId =>
            put {
              moveRover(roverId)
            }
          } ~
          path(Segment / "turn-left") { roverId =>
            put {
              turnRoverLeft(roverId)
            }
          } ~
          path(Segment / "turn-right") { roverId =>
            put {
              turnRoverRight(roverId)
            }
          }
      }
}

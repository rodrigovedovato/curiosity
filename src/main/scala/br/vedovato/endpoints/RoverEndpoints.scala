package br.vedovato.endpoints

import akka.http.scaladsl.server.Directives._

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import br.vedovato.model.Rover
import br.vedovato.modules.RoverModule
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import br.vedovato.model.Directions.{ circeEncoder, circeDecoder }

import scala.util.{ Failure, Success }

trait RoverEndpoints extends FailFastCirceSupport {
  def roverModule: RoverModule

  lazy val all: Route =
    path("rovers") {
      post {
        entity(as[Rover]) { rover =>
          onComplete(roverModule.landRover(rover)) {
            case Success(value) => complete(StatusCodes.Created -> value)
            case Failure(_) => complete(StatusCodes.InternalServerError)
          }
        }
      }
    }
}

package br.vedovato.modules

import akka.actor.ActorSystem
import akka.stream.Materializer
import br.vedovato.model.{Rover, RoverLandingResponse}
import br.vedovato.persistence._
import br.vedovato.service.NamingService
import java.time.{OffsetDateTime, ZoneOffset}

import scala.concurrent.{ExecutionContext, Future}

class RoverModule(implicit ec: ExecutionContext, sys: ActorSystem, mat: Materializer) {
  private val roverPersistence = InMemoryRoverPersistence()

  def landRover(rover: Rover): Future[RoverLandingResponse] = {
    for {
      randomName <- NamingService.getRandomName.map(_.trim)
      landingResponse <- roverPersistence.push(rover.copy(name = Some(randomName))).map { roverId =>
        RoverLandingResponse(
          name = randomName,
          landingTime = OffsetDateTime.now(ZoneOffset.UTC).toEpochSecond,
          roverActions = Map(
            "move" -> s"/hovers/$roverId/move",
            "turn-left" -> s"/hovers/$roverId/turn-left",
            "turn-right" -> s"/hovers/$roverId/turn-right",
          )
        )
      }
    } yield landingResponse
  }
}

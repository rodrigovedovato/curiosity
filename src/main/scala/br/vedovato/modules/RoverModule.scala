package br.vedovato.modules

import akka.actor.ActorSystem
import akka.stream.Materializer
import br.vedovato.model.{Rover, RoverId, RoverLandingResponse}
import br.vedovato.persistence._
import br.vedovato.service.NamingService
import java.time.{OffsetDateTime, ZoneOffset}

import br.vedovato.ops._
import scalacache.Cache

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

class RoverModule(implicit ec: ExecutionContext, sys: ActorSystem, mat: Materializer, c: Cache[Rover]) {
  private val roverPersistence = InMemoryRoverPersistence()

  def landRover(rover: Rover): Future[RoverLandingResponse] = {
    for {
      randomName <- NamingService.getRandomName.map(_.trim)
      landingResponse <- roverPersistence.push(rover.copy(name = Some(randomName))).map { roverId =>
        RoverLandingResponse(
          id = roverId,
          name = randomName,
          landingTime = OffsetDateTime.now(ZoneOffset.UTC).toEpochSecond,
          roverActions = Map(
            "move" -> s"/rover/$roverId/move",
            "turn-left" -> s"/rover/$roverId/turn-left",
            "turn-right" -> s"/rover/$roverId/turn-right",
          )
        )
      }
    } yield landingResponse
  }

  def getRover(roverId: RoverId): Future[Option[Rover]] = roverPersistence.pull(roverId)

  def getAllRovers: Future[Seq[Rover]] = roverPersistence.getAll

  def turnLeft(roverId: RoverId): Future[Option[Rover]] = {
    roverPersistence.pull(roverId).map(_.map(_.turnLeft)) andThen {
      case Success(rover) if rover.isDefined =>
        roverPersistence.push(rover.get, Some(roverId))
    }
  }

  def turnRight(roverId: RoverId): Future[Option[Rover]] = {
    roverPersistence.pull(roverId).map(_.map(_.turnRight)) andThen {
      case Success(rover) if rover.isDefined =>
        roverPersistence.push(rover.get, Some(roverId))
    }
  }

  def move(roverId: RoverId): Future[Option[Either[CommandFeedback, Rover]]] = {
    roverPersistence.pull(roverId).map(_.map(_.safeMove)) andThen {
      case Success(rover) if rover.isDefined && rover.get.isRight =>
        roverPersistence.push(rover.get.right.get, Some(roverId))
    }
  }
}

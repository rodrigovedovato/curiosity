package br.vedovato.persistence

import br.vedovato.model.{ Rover, RoverId }
import scalacache._
import scalacache.modes.scalaFuture._

import scala.concurrent.{ ExecutionContext, Future }

trait RoverPersistence {
  private[persistence] implicit def cache: Cache[Rover]

  def push(rover: Rover)(implicit ec: ExecutionContext): Future[RoverId] = {
    val roverId: String = java.util.UUID.randomUUID().toString.substring(0, 8)
    cache.put(keyParts = "rover", roverId)(rover).map(_ => roverId)
  }

  def pull(roverId: String)(implicit ec: ExecutionContext): Future[Option[Rover]] = {
    cache.get(keyParts = "rover", roverId)
  }
}

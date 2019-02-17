package br.vedovato.persistence

import br.vedovato.model.{ Rover, RoverId }

import scala.concurrent.{ ExecutionContext, Future }

trait RoverPersistence {
  private[persistence] def source: DataSource[Rover, RoverId]

  def push(rover: Rover, roverId: Option[RoverId] = None)(implicit ec: ExecutionContext): Future[RoverId] = {
    source.addOrUpdate(roverId.getOrElse(java.util.UUID.randomUUID().toString.substring(0, 8)), rover)
  }

  def pull(roverId: String)(implicit ec: ExecutionContext): Future[Option[Rover]] = source.getByKey(roverId)

  def getAll(implicit ec: ExecutionContext): Future[Seq[Rover]] = source.getAll
}

package br.vedovato.persistence

import br.vedovato.model.{ Rover, RoverId }
import br.vedovato.persistence.datasources.DataSource

import scala.concurrent.{ ExecutionContext, Future }

trait RoverPersistence {
  private[persistence] def source: DataSource[Rover, RoverId]

  def push(rover: Rover)(implicit ec: ExecutionContext): Future[RoverId] = {
    val r = rover.id match {
      case Some(_) => rover
      case None => rover.copy(id = Some(java.util.UUID.randomUUID().toString.substring(0, 8)))
    }

    source.addOrUpdate(r.id.get, r)
  }

  def pull(roverId: String)(implicit ec: ExecutionContext): Future[Option[Rover]] = source.getByKey(roverId)

  def getAll(implicit ec: ExecutionContext): Future[Seq[Rover]] = source.getAll
}

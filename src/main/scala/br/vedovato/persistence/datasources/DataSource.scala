package br.vedovato.persistence.datasources
import scala.concurrent.Future

trait DataSource[Content, Key] {
  def getByKey(key: Key): Future[Option[Content]]
  def getAll: Future[Seq[Content]]
  def addOrUpdate(key: Key, data: Content): Future[Key]
}

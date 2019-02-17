package br.vedovato.persistence.datasources

import br.vedovato.persistence.DataSource

import scala.concurrent.{ Await, ExecutionContext, Future }
import scalacache._
import scalacache.modes.scalaFuture._

import scala.collection.mutable
import scala.util.{ Failure, Success }

import scala.concurrent.duration._

trait Scalacache[Content] extends DataSource[Content, String] {
  private[persistence] implicit def ec: ExecutionContext
  private[persistence] implicit def c: Cache[Content]

  def keySet: mutable.Set[String]

  override def getByKey(key: String): Future[Option[Content]] = {
    get(key)
  }

  override def getAll: Future[Seq[Content]] = {
    val allData = keySet.toSeq.map(key => getByKey(key))

    Future {
      allData.map { f =>
        Await.result(f, 5 seconds).get
      }
    }
  }

  override def addOrUpdate(key: String, data: Content): Future[String] = {
    put(key)(data).andThen {
      case Success(_) => if (keySet(key) == false) {
        keySet.add(key)
      }
      case Failure(err) => throw err
    } map (_ => key)
  }
}

object Scalacache {
  val keys: mutable.Set[String] = mutable.Set[String]()

  def apply[Content](implicit context: ExecutionContext, cache: Cache[Content]): Scalacache[Content] = {
    new Scalacache[Content] {
      override private[persistence] implicit def ec: ExecutionContext = context
      override private[persistence] implicit def c: Cache[Content] = cache
      override def keySet: mutable.Set[String] = keys
    }
  }
}
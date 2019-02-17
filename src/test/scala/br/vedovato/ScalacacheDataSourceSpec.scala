package br.vedovato.persistence

import br.vedovato.persistence.datasources.Scalacache
import org.scalatest.FlatSpec
import scalacache.Cache
import scalacache.caffeine.CaffeineCache

import scala.collection.mutable
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class ScalacacheDataSourceSpec extends FlatSpec {

  def setupPersistence: Scalacache[String] = {
    implicit val stringCache: Cache[String] = CaffeineCache[String]

    val keys = mutable.Set[String]()

    new Scalacache[String] {
      override def keySet: mutable.Set[String] = keys
      override private[persistence] implicit def ec: ExecutionContext =
        scala.concurrent.ExecutionContext.Implicits.global
      override private[persistence] implicit def c: Cache[String] = stringCache
    }
  }

  "Persistence" should "return new key when new data is added" in {
    val persistence = setupPersistence

    val key = Await.result(persistence.addOrUpdate("TEST_KEY", "CONTENT"), 2 seconds)
    assertResult("TEST_KEY")(key)
  }

  "Persistence" should "return 4 items" in {
    val persistence = setupPersistence

    val keys = Future.sequence(
      Seq(
        persistence.addOrUpdate("1", "CONTENT"),
        persistence.addOrUpdate("2", "CONTENT"),
        persistence.addOrUpdate("3", "CONTENT"),
        persistence.addOrUpdate("4", "CONTENT")))

    val insertedKeys = Await.result(keys, 5 seconds)
    val allData = Await.result(persistence.getAll, 5 seconds)

    assertResult(insertedKeys.length)(allData.length)
  }
}

package br.vedovato
import br.vedovato.model.{ Rover, RoverId }
import br.vedovato.persistence.datasources.{ DataSource, Scalacache }
import scalacache.Cache
import scalacache.caffeine.CaffeineCache

import scala.concurrent.ExecutionContext

package object persistence {

  object Implicits {
    implicit val roverCache: Cache[Rover] = CaffeineCache[Rover]
  }

  object InMemoryRoverPersistence {
    def apply()(implicit ec: ExecutionContext, c: Cache[Rover]): RoverPersistence = new RoverPersistence {
      override private[persistence] def source: DataSource[Rover, RoverId] = Scalacache.apply[Rover]
    }
  }
}

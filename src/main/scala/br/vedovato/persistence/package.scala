package br.vedovato
import br.vedovato.model.Rover
import scalacache.Cache

package object persistence {
  object InMemoryRoverPersistence {
    def apply(): RoverPersistence = new RoverPersistence {
      import scalacache.caffeine._
      override implicit def cache: Cache[Rover] = CaffeineCache[Rover]
    }
  }
}

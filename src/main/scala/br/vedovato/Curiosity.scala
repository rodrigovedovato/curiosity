package br.vedovato

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import br.vedovato.endpoints.RoverEndpoints
import br.vedovato.modules.RoverModule
import br.vedovato.persistence.Implicits.roverCache

import scala.util.{ Failure, Success }

import scala.concurrent.ExecutionContext.Implicits.global

object Curiosity extends App with RoverEndpoints {
  implicit val sys: ActorSystem = ActorSystem()
  implicit val mat: ActorMaterializer = ActorMaterializer()

  override def roverModule = new RoverModule()

  Http().bindAndHandle(roverRoutes, "localhost", 8080) onComplete {
    case Success(bound) =>
      println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
  }
}

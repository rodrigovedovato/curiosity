package br.vedovato.service
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ HttpMethods, HttpRequest, Uri }
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer

import scala.concurrent.{ ExecutionContext, Future }

object NamingService {
  def getRandomName(implicit ec: ExecutionContext, sys: ActorSystem, mat: Materializer): Future[String] = {
    val request = HttpRequest(HttpMethods.GET, Uri("https://frightanic.com/goodies_content/docker-names.php"))
    Http().singleRequest(request).flatMap(r => Unmarshal(r.entity).to[String]).recover {
      case _ => "No name for you"
    }
  }
}

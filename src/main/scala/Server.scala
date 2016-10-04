import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}

import scala.concurrent.ExecutionContext

object Server extends App {
  implicit val system: ActorSystem = ActorSystem("http-system")

  implicit val executor: ExecutionContext = system.dispatcher

  implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(system))

  val route = {
    pathPrefix("ping") {
      post {
        entity(as[String]) { str =>
          complete( (StatusCodes.OK, "pong") )
        }
      }
    }
  }

  Http(system).bindAndHandle(route, "localhost", 9000)

}

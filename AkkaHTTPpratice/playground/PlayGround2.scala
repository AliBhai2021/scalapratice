package AkkaHTTPpratice.playground

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse, StatusCodes, Uri}
import akka.stream.ActorMaterializer

object PlayGround2 extends App {

  implicit val system = ActorSystem("PlayPratice")
  implicit val materializer = ActorMaterializer()

  val myroute :HttpRequest => HttpResponse = {
    case HttpRequest(HttpMethods.GET,Uri.Path("/home"),_,_,_)=>

        HttpResponse(
          StatusCodes.OK,
          entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
            """
              |<html>
              | <body>
              |   Hello from Akka HTTP!
              | </body>
              |</html>
          """.stripMargin)
        )
    case request:HttpRequest =>
      request.discardEntityBytes()
      HttpResponse(
        StatusCodes.NotFound, // 404
        entity = HttpEntity(
          ContentTypes.`text/html(UTF-8)`,
          """
            |<html>
            | <body>
            |   OOPS! The resource can't be found.
            | </body>
            |</html>
          """.stripMargin
        ))
  }

   // Http().bindAndHandle( handler = routes, interface = bindInterface, port = port,settings = settings )
  val server1 = Http().bindAndHandleSync(myroute,"localhost",8061)

}

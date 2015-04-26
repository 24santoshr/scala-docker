package se.marcuslonnberg.scaladocker.remote.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.Uri.Path

import scala.concurrent.Future

trait HostCommands extends DockerCommands {
  def ping(): Future[Unit] = {
    sendGetRequest(Path / "_ping").map { response =>
      response.status match {
        case StatusCodes.OK => ()
        case statusCode =>
          throw new ServerErrorException(statusCode, "Ping unsuccessful")
      }
    }
  }
}
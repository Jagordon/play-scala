package services

import scalaj.http._

object connector  {

  def send(content: String): HttpResponse[String] = {
    val request = Http("http://localhost:9001/").postData(content)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 5000).asString
    request match {
      case request if(request.isError == true) => request
      case _ => request
    }
  }
}
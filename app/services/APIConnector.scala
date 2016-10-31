package services

import scalaj.http._
import javax.inject._

trait APIConnector {
  def send(content: String): Boolean
}

@Singleton
class HttpAPIConnector extends APIConnector {

  def send(content: String) = {
    try {
      val request = Http("http://localhost:9001/api").postData(content)
        .header("Content-Type", "application/json")
        .header("Charset", "UTF-8")
        .timeout(connTimeoutMs = 1000, readTimeoutMs = 5000).asString
        request match {
          case r if r.isError => false
          case _ =>                          true
        }
    } catch {
      case ste: java.net.SocketTimeoutException => false
    }
  }
}
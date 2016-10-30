package model

import play.api.libs.json.Json

case class myModel (name : String, email : String)

  object myModel {
    implicit val formats = Json.format[myModel]
  }
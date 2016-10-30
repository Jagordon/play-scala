package forms

import play.api.data.Form
import play.api.data.Forms._
import forms.validator._

case class submissionForms(name: String, email: String)

object submissionForms {
  val form = Form[submissionForms](
    mapping(
      "name" -> nonEmptyText(),
      "email" ->text().verifying("Please enter a valid email like your.name@provider.com", isValidEmail(_))
    )(submissionForms.apply)(submissionForms.unapply)
  )
}




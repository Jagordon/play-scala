package controllers

import javax.inject._

import forms.submissionForms
import play.api.mvc._
import services.connector.send


@Singleton
class HomeController @Inject() extends Controller {

  def present() = Action {
    implicit request =>
    Ok(views.html.index(submissionForms.form, "Your new application is ready."))
  }

  def submit() = Action {
    implicit request =>
    submissionForms.form.bindFromRequest().fold(
      hasErrors => BadRequest(views.html.index(hasErrors, "Your submission has errors")),
      success => {
        val dataSubmission = s"""{"name":"${success.name}","email":"${success.email}"}"""
        val response = send(dataSubmission)
        if (response) {
          Ok(views.html.success(submissionForms.form, "We did it!"))
        } else {
          Ok(views.html.index(submissionForms.form, "Your submission has errors"))
        }
      }
    )
  }
}

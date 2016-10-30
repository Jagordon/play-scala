import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "HomeController" should {

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Your new application is ready.")
    }

    "successfully submit with good data" in {
      val controller = new controllers.HomeController()
      val request = FakeRequest(POST, "/")
        .withFormUrlEncodedBody(
          "name" -> "Richard",
          "email" -> "joe@joe.com"
        )
      val result = controller.submit()(request)
      status(result) mustBe OK
      contentAsString(result) must include ("You have successfully submitted your data.")
    }

    "fail to submit with bad data" in {
      val controller = new controllers.HomeController()
      val request = FakeRequest(POST, "/")
        .withFormUrlEncodedBody(
          "name" -> "",
          "email" -> "222"
        )
      val result = controller.submit()(request)
      status(result) mustBe 400
      contentAsString(result) must include ("Your submission has errors")
    }

  }

}

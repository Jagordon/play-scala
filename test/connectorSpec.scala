import org.scalatestplus.play._
import services.connector.send

class connectorSpec extends PlaySpec with OneAppPerTest {

  val goodData = """{"name":"Johnny Walker","email":"John@filson.co.uk"}"""
  val badData = "what is going on"

    "connector" should {
      "return true on successful submission" in {
        send(goodData) mustBe true
      }
      "return false on bad submission" in {
        send(badData) mustBe false
      }
    }

}

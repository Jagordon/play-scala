package forms

object validator {
  def isValidEmail(email: String): Boolean = """([\w\.].+)@([\w\.]+)""".r.unapplySeq(email).isDefined
}

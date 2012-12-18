package exceptions

class UndefinedFieldException(val field: String) extends ScalaMarcException {
  override def getMessage = "Undefined field: " + field
}
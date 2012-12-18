package exceptions

class InvalidRecordLengthException(val declaredLength: Int, val actualLength: Int) extends ScalaMarcException {

  override def getMessage = "Declared length: " + declaredLength + " Actual length: " + actualLength
}
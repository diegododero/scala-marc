package exceptions

class InvalidLeaderFormatException(val leader: String) extends ScalaMarcException {
	override def getMessage = "Invalid leader: " + leader
}
package marc

class Subfield(val code: Char, val value: String) {

  override def toString = "$" + code + " " + value

}
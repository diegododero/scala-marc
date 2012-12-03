package marc

class Subfield(code: Char, value: String) {
  
  override def toString = "$" + code + " " + value

}
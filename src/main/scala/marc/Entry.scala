package marc

class Entry(val tag: String, val length: String, val offset: String) {
  
  override def toString = tag + " " + length + " " + offset

}
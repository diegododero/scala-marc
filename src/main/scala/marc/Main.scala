package marc

import io.MarcReader
import io.MarcParser
import marc2xml.MarcXMLWriter

object Main extends App {
  val reader = new ForgivingMarcReader(this.args(0))
  reader.start
  println(reader.getNext)
  println(reader.getNext)
  println(reader.getNext)
}
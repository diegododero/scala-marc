package marc

import io.MarcReader
import io.MarcParser
import marc2xml.MarcXMLWriter
import io.ForgivingMarcReader

object Main extends App {
  val reader = new ForgivingMarcReader(this.args(0))
  reader.start
  println(reader.next.toDublinCore)
  println(reader.next.toDublinCore)
  println(reader.next.toDublinCore)
  println(reader.next.toDublinCore)
  val r = new MarcReader("src/test/scala/marc/sandburg.mrc")
  r.start
  println(r.next.toDublinCore)
}
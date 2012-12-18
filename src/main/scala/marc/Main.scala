package marc

import io.MarcReader
import io.MarcParser
import marc2xml.MarcXMLWriter

object Main extends App {
  val reader = new ForgivingMarcReader("/home/diego/Descargas/data/hlom/ab.bib.00.20121123.full.mrc")
  reader.start
  println(reader.getNext)
  println(reader.getNext)
  println(reader.getNext)
}
package test

import io.MarcReader
import io.MarcParser
import marc2xml.MarcXMLWriter

object PrintTitles extends App {
  val reader = new MarcReader("/home/diego/Descargas/data/hlom/ab.bib.00.20121123.full.mrc")
  /*while (reader.hasNext) {
    val record = reader.next
    record.getFieldsByTag("245").map(f =>
      println(f.subfields.get('a').get))
  }*/
}
package test

import io.MarcReader
import io.MarcParser
import marc2xml.MarcXMLWriter

object Harvard extends App {
  val reader = new MarcReader("/home/diego/Descargas/data/hlom/ab.bib.00.20121123.full.mrc")
  //val records = reader.records
  reader.map{ record =>
  	println(record)
  }
  /*val s = reader.next
  println(s)*/
}
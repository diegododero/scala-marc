package test

import io.MarcReader
import io.MarcParser
import marc2xml.MarcXMLWriter

object Harvard extends App {
	val reader = new MarcReader("/home/diego/Descargas/data/hlom/ab.bib.00.20121123.full.mrc")
	val recordData = reader.next
	println(recordData)
	val parser = new MarcParser
	println(parser.parse(recordData))
	MarcXMLWriter.write("harvard.xml", parser.parse(recordData))
	val s = reader.next
	println(parser.parse(s))
}
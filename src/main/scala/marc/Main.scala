package marc

import io.MarcReader
import io.MarcParser
import marc2xml.MarcXMLWriter
import io.ForgivingMarcReader
import exceptions.ScalaMarcException

object Main extends App {
  val reader = new ForgivingMarcReader(this.args(0))
  while (reader.hasNext){
    try{
    	val r = reader.next
    	println(r.toDublinCore)
    }
    catch{
      case sme: ScalaMarcException => println(sme.getMessage)
    }   
  }
}
package marc2xml

import scala.xml.Node
import java.io.FileOutputStream
import java.nio.channels.Channels
import marc.Record

object MarcXMLWriter {
  def write(filename: String, record: Record) = save(record.toXML, filename) //scala.xml.XML.save(filename, record.toXML)

  val Encoding = "UTF-8"

  def save(node: Node, fileName: String) = {

    val pp = new MarcPrettyPrinter(80, 2)
    val fos = new FileOutputStream(fileName)
    val writer = Channels.newWriter(fos.getChannel(), Encoding)

    try {
      writer.write("<?xml version='1.0' encoding='" + Encoding + "'?>\n")
      writer.write(pp.format(node))
    } finally {
      writer.close()
    }

    fileName
  }
}
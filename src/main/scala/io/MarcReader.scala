package io

import marc.Marc
import scala.collection.Iterator

class MarcReader(val filename: String) extends Iterator[String] {
  val source = scala.io.Source.fromFile(filename)

  def getNextRecord: String = {
    val sb = new StringBuilder
    while ((source.hasNext) && (source.next != Marc.END_OF_RECORD)) {
      sb += source.ch
    }
    if (source.ch != Marc.END_OF_RECORD) {
      throw new Exception("Malformed record")
    }
    sb.toString
  }

  def reset: Unit = source.reset

  def next = getNextRecord

  def hasNext = source.hasNext

}
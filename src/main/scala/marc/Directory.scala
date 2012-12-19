package marc

import scala.collection.mutable.MutableList

/**
 * The class Directory implements the directory part of the
 * MARC record
 * It's created before saving the MARC record to a file
 * @author diego
 *
 */
class Directory {
  val entries: MutableList[Entry] = MutableList()

  override def toString = entries.mkString("\n") //.toSeq.sortWith(_._2.offset < _._2.offset).toMap.mkString("\n")

  def addEntry(e: Entry) = {
    entries += e
  }
  
  def toTransmissionFormat: String = 
    entries.map(e => e.tag + e.length + e.offset).mkString + Marc.FIELD_DELIMITER.toString
}
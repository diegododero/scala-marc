package io

import marc.Marc
import scala.collection.Iterator
import exceptions.InvalidRecordLengthException
import marc.Record
import scala.collection.mutable.MutableList

/**
 * A class for reading MARC record from a file
 * @author diego
 *
 */
class MarcReader(val filename: String) {
  var stream: scala.io.Source = scala.io.Source.fromFile(filename)

  def records: List[Record] = {
    val source = scala.io.Source.fromFile(filename)
    val records = MutableList[Record]()
    while (source.hasNext) {
      val raw = getRecord(source)
      records += MarcParser.parse(raw)
    }
    records.toList
  }
  
  def start: Unit = {
    stream = scala.io.Source.fromFile(filename)
  }
  
  def getNextData: String = {
    val raw = getRecord(stream)
    raw
  }
  
  def next: Record = {
    val raw = getRecord(stream)
    MarcParser.parse(raw)
  }
  
  protected def getRecord(source: scala.io.Source): String = {
      val length = source.take(Marc.RECORD_LENGTH_SIZE).toList.mkString
      val rest = source.take(length.toInt - Marc.RECORD_LENGTH_SIZE).toList.mkString
      if ((rest.size < length.toInt - Marc.RECORD_LENGTH_SIZE) || (rest.charAt(rest.length - 1) != Marc.END_OF_RECORD)) {
        throw new InvalidRecordLengthException(length.toInt, rest.size)
      }
      length ++ rest
  }

  def decode(data: String) = MarcParser.parse(data)
}
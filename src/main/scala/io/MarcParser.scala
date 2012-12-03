package io

import marc.ControlField
import marc.DataField
import marc.Directory
import marc.Entry
import marc.Field
import marc.Leader
import marc.Marc
import marc.Record
import scala.Array.canBuildFrom
import exceptions.RecordLengthInvalidException
import marc.Subfield

class MarcParser {
  private var byteArray: Array[Byte] = new Array[Byte](10)

  def parse(recordData: String): Record = {
    byteArray = recordData.map(_.toByte).toArray
    val recordLengthInLeader = leaderData.slice(0, 5)
    if (recordLengthInLeader.toInt != recordSize){
      throw new RecordLengthInvalidException
    }
    val record = new Record
    getControlFields(record)
    getDataFields(record)    
    record
  }
  
  def recordSize = byteArray.size + 1

  private def leaderData = byteArray.slice(0, Marc.LEADER_LENGTH).map(_ toChar).mkString("")

  def directory = byteArray.slice(Marc.LEADER_LENGTH, byteArray.size - 1).takeWhile(_ != Marc.FIELD_DELIMITER)
  def dataFields = byteArray.slice(Marc.LEADER_LENGTH + directory.length + 1, byteArray.size - 1)

  def createDirectory: Directory = {
    val d = new Directory
    val entries = directory.map(_ toChar).mkString("").grouped(Marc.DIRECTORY_ENTRY_LENGTH).toList
    entries.map(entry =>
      d.addEntry(new Entry(entry.slice(Marc.ENTRY_TAG_FIELD_START, Marc.ENTRY_TAG_FIELD_LENGTH), entry.slice(3, 7), entry.slice(7, 12))))
    d
  }

  def getFields = {
    dataFields.map(_ toChar).mkString("").split(Marc.FIELD_DELIMITER)
  }
  
  def mergeDirectoryWithFields = createDirectory.entries.zip(getFields)
  
  def getIndicator(data: String, index: Int) = {
    data.split(Marc.SUBFIELD_DELIMITER).toList.head.charAt(index)
  }
  
  def getSubfields(data: String) = {
    val subfields = data.split(Marc.SUBFIELD_DELIMITER).toList.tail
    subfields.foldLeft(scala.collection.mutable.Map[Char, String]())((m, s) => m += (s.charAt(0) -> s.substring(1)))
  }
  
  def getDataField(tag: String, data: String): DataField = {
    val field = new DataField(tag, getIndicator(data, 0), getIndicator(data, 1))
     getSubfields(data).map(subfield => field.addSubfield(new Subfield(subfield._1, subfield._2)))
    field
  }
  
  def getControlField(tag: String, data: String): ControlField = new ControlField(tag, data)
 
  def getControlFields(record: Record): Unit = {
    mergeDirectoryWithFields.filter(t => isControlTag(t._1.tag)).sortWith(_._1.tag > _._1.tag).map(x => record.addField(getControlField(x._1.tag, x._2)))
  }
  
  def getDataFields(record: Record): Unit = {
    mergeDirectoryWithFields.filter(t => !isControlTag(t._1.tag)).sortWith(_._1.tag > _._1.tag).map(x => record.addField(getDataField(x._1.tag, x._2)))
  }
  
  def isControlTag(tag: String): Boolean = tag.startsWith(Marc.CONTROL_TAG_PREFIX)  

}
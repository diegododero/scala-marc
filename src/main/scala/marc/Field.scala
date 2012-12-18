package marc

import scala.xml.Elem
import scala.collection.mutable.MutableList

abstract class Field {
  def tag: String
  def toXML: Elem
  def isControlField: Boolean = false
  def isSubjectField: Boolean = tag.startsWith(Marc.SUBJECT_FIELD_TAG_PREFIX)
  def subfields: MutableList[Subfield]
  def apply(code: Char): Option[Subfield]
  def toTransmissionFormat: String
  def length: Int = toTransmissionFormat.size
}
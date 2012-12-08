package marc

import scala.xml.Elem
import scala.collection.mutable.MutableList

class ControlField(val tag: String, data: String) extends Field {
  override def toString = tag + " " + data

  override def apply(code: Char): Option[Subfield] = None

  override def isControlField: Boolean = true

  def subfields = MutableList[Subfield]()

  def isControlNumberField = tag.startsWith(Marc.CONTROL_NUMBER_TAG_PREFIX)

  def toXML: Elem =
    <controlfield tag={ tag }>{ data }</controlfield>

}
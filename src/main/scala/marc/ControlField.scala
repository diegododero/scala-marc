package marc

import scala.collection.mutable.MutableList

class ControlField(val tag: String, data: String) extends Field {
	override def toString = tag + " " + data 
	
	override def isControlField = true
	
	def subfields = MutableList[Subfield]()
	
	def isControlNumberField = tag.startsWith(Marc.CONTROL_NUMBER_TAG_PREFIX)
	
	def toXML =
	  <controlfield tag={tag}>{data}</controlfield>
	  
}
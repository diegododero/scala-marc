package marc

import scala.xml.Elem
import scala.collection.mutable.MutableList

abstract class Field
{
	def tag: String
	def toXML: Elem
	def isControlField = false
	def isSubjectField = tag.startsWith(Marc.SUBJECT_FIELD_TAG_PREFIX)
	def subfields: MutableList[Subfield]
}
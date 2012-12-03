package marc

import scala.collection.mutable.Map

class DataField(val tag: String, indicator1: Char, indicator2: Char, val subfields: Map[Char, String]) extends Field {
	override def toString = getSortedSubfields.map(t => tag + " " + indicator1 + indicator2 + "$" + t._1 + " " + t._2).mkString("\n")
  
	def addSubfield(code: Char, data: String) {
	  subfields += code -> data
	}
	
	private def getSortedSubfields = subfields.toList.sortWith(_._1 < _._1)
	
	def toXML =
	<datafield tag={tag} ind1={indicator1.toString.trim} ind2={indicator2.toString.trim}>
		{getSortedSubfields.map(p => <subfield code={p._1.toString}>{p._2}</subfield>)}
	</datafield>
}
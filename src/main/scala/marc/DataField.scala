package marc

import scala.collection.mutable.MutableList

class DataField(val tag: String, indicator1: Char, indicator2: Char) extends Field {
  val subfields = MutableList[Subfield]()
  
  override def toString = getSortedSubfields.map(t => tag + " " + indicator1 + indicator2 + "$" + t.toString).mkString("\n")

  def addSubfield(subfield: Subfield) {
    subfields += subfield
  }

  private def getSortedSubfields = subfields.toList.sortWith(_.code < _.code)

  def toXML =
    <datafield tag={ tag } ind1={ indicator1.toString.trim } ind2={ indicator2.toString.trim }>
      { getSortedSubfields.map(p => <subfield code={ p.code.toString }>{ p.code }</subfield>) }
    </datafield>
}
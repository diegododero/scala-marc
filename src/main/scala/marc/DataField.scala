package marc

import scala.xml.Elem
import scala.collection.mutable.MutableList

class DataField(val tag: String, indicator1: Char, indicator2: Char) extends Field {
  val subfields = MutableList[Subfield]()

  override def toString = getSortedSubfields.map(t => tag + " " + formatIndicator(indicator1) + formatIndicator(indicator2)  + " " + t.toString).mkString("\n")

  override def apply(code: Char): Option[Subfield] = subfields.find(s => s.code == code)
  
  private def formatIndicator(ind: Char)= if (ind.isWhitespace) Marc.EMPTY_INDICATOR else ind

  def addSubfield(subfield: Subfield): Unit = {
    subfields += subfield
  }

  private def getSortedSubfields = subfields.toList.sortWith(_.code < _.code)
  
  override def toTransmissionFormat = 
    indicator1.toString + indicator2.toString +
    subfields.map(s => Marc.SUBFIELD_DELIMITER + s.code.toString + s.value).mkString +
    Marc.FIELD_DELIMITER

  def toXML: Elem =
    <datafield tag={ tag } ind1={ indicator1.toString.trim } ind2={ indicator2.toString.trim }>
      { getSortedSubfields.map(p => <subfield code={ p.code.toString }>{ p.value }</subfield>) }
    </datafield>
}
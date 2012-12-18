package marc

import scala.collection.mutable.MutableList
import scala.xml.Elem

class Record(val leader: Leader) {
  val fields = MutableList[Field]()

  override def toString =
    "Leader " +
      leader.value + "\n" +
      fields.sortWith(_.tag < _.tag).map(_ toString).mkString("\n")

  def apply(tag: String) = fields.find(f => f.tag == tag)

  def getFieldsByTag(tag: String): List[Field] = fields.filter(f => f.tag == tag).toList

  def controlFields: List[ControlField] = fields.filter(f => f.isControlField).map(f => f.asInstanceOf[ControlField]).toList

  def dataFields: List[DataField] = fields.filter(f => !f.isControlField).map(f => f.asInstanceOf[DataField]).toList

  def addField(field: Field): Unit = fields += field

  def toDublinCore: DublinCore = new DublinCore(this)
  
  private def sortedFields: MutableList[Field] = fields.sortWith(_.tag < _.tag)
  
  def directory: Directory = {
    val directory = new Directory
    var offset = 0
    sortedFields.map(f => 
      {    	
    	directory.addEntry(new Entry(f.tag, "%04d".format(f.length), "%05d".format(offset)))
    	offset = offset + f.length
      })  
    directory
  }
  
  def base: String = leader.value + directory.toTransmissionFormat
  
  def recordFields: String = sortedFields.map(_ toTransmissionFormat).mkString
  
  def toTransmissionFormat: String = 
    leader.update(base.size + recordFields.size + 1, base.size).value +
    directory.toTransmissionFormat +
    recordFields +
    Marc.END_OF_RECORD

  def toXML: Elem =
    <record>
      { leader.toXML }
      { fields.map(_.toXML) }
    </record>
}
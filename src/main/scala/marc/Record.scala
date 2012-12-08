package marc

import scala.collection.mutable.MutableList
import scala.xml.Elem

class Record(val leader: Leader) {
  val fields = MutableList[Field]()

  override def toString =
    "Leader\n" +
      leader.toString + "\n" +
      //"Directory \n" +
      //directory.toString + "\n" + 
      "Fields \n" +
      fields.map(_ toString).mkString("\n")
  //controlFields.map(_ toString).mkString("\n") +
  //dataFields.map(_ toString).mkString("\n")   

  def apply(tag: String) = fields.find(f => f.tag == tag)

  def getFieldsByTag(tag: String): List[Field] = fields.filter(f => f.tag == tag).toList

  def controlFields: List[ControlField] = fields.filter(f => f.isControlField).map(f => f.asInstanceOf[ControlField]).toList

  def dataFields: List[DataField] = fields.filter(f => !f.isControlField).map(f => f.asInstanceOf[DataField]).toList

  def addField(field: Field): Unit = fields += field

  def toDublinCore: DublinCore = new DublinCore(this)

  def toXML: Elem =
    <record>
      { leader.toXML }
      { fields.map(_.toXML) }
    </record>
}
package marc

import scala.collection.mutable.MutableList

class Record {
  val leader = new Leader
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
    
  def getFieldsByTag(tag: String): List[Field] = fields.filter(f => f.tag == tag).toList
  
  def controlFields: List[ControlField] = fields.filter(f => f.isControlField).map(f => f.asInstanceOf[ControlField]).toList
  
  def dataFields: List[DataField] = fields.filter(f => !f.isControlField).map(f => f.asInstanceOf[DataField]).toList
  
  def addField(field: Field) = fields += field
  
  def toDublinCore = new DublinCore(this)
  
  def toXML =
    <record>
  	{leader.toXML}
  	{ fields.map(_.toXML) }
  </record>
}
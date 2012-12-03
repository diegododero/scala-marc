package marc

class Record(val leader: Leader, val directory: Directory, val controlFields: List[ControlField], val dataFields: List[DataField]) {
  
  override def toString = 
    "Leader\n" +
    leader.toString + "\n" + 
    "Directory \n" +
    directory.toString + "\n" + 
    "Fields \n" +
    controlFields.map(_ toString).mkString("\n") +
    dataFields.map(_ toString).mkString("\n")   
    
  def getDataFieldsByTag(tag: String): List[DataField] = dataFields.filter(f => f.tag == tag)
  
  def toDublinCore = new DublinCore(this)
  
  def toXML =
    <record>
  	{leader.toXML}
  	{ controlFields.map(_.toXML) }
  	{ dataFields.map(_.toXML) }  	
  </record>
}
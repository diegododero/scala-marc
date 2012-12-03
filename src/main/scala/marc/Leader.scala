package marc

class Leader(value: String) {
  
  override def toString = value
  
  def recordLength = value.slice(0, 5)
  def recordStatus = value.slice(5, 5)
  def typeOfRecord = value.slice(6, 6)
  def characterCoding = value.slice(9, 9)
  def baseAddress = value.slice(12, 16)
  def entryMap = value.slice(20, 23)
  
  def toXML = 
  	<leader>{value}</leader>
}
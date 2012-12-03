package marc

class Leader {
  var value = new StringBuilder(" " * Marc.LEADER_LENGTH).replace(10, 11, "22").replace(20, 23, "4500").toString
  
  override def toString = value
  
  def recordLength = value.slice(0, 5)
  def recordStatus = value.slice(5, 5)
  def typeOfRecord = value.slice(6, 6)
  def characterCoding = value.slice(9, 9)
  def baseAddress = value.slice(12, 16)
  def entryMap = value.slice(20, 23)
  
  def setRecordLength(length: String) {
    value = new StringBuilder(value).replace(0, 4, length).toString
  }
  
  def toXML = 
  	<leader>{value}</leader>
}
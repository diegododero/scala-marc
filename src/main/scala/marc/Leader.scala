package marc

import scala.xml.Elem

/**
 * Values for fields taken from http://www.loc.gov/marc/bibliographic/bdleader.html
 */
class Leader(
  val recordLength: Int,
  /* Possible values for record status */
  // a - Increase in encoding level
  // c - Corrected or revised
  // d - Deleted
  // n - New
  // p - Increase in encoding level from prepublication
  val recordStatus: String,
  /* Possible values for type of record */
  // a - Language material
  // c - Notated music
  // d - Manuscript notated music
  // e - Cartographic material
  // f - Manuscript cartographic material
  // g - Projected medium
  // i - Nonmusical sound recording 
  val recordType: String,
  /* Possible values for bibliographical level */  
  // a - Monographic component part
  // b - Serial component part
  // c - Collection
  // d - Subunit     
  // i - Integrating resource
  // m - Monograph/Item
  // s - Serial 
  val bibliographicLevel: String,
  /* Possible values for type of control */
  // # - No specified type
  // a - Archival 
  val typeOfControl: String,
  /* Possible values coding scheme */
  // # - MARC-8
  // a - UCS/Unicode   
  val codingScheme: String,
  val indicatorCount: Int,
  val subfieldCodeCount: Int,
  val baseAddress: Int,
  /* Possible values for encoding level */  
  // # - Full level
  // 1 - Full level, material not examined
  // 2 - Less-than-full level, material not examined
  // 3 - Abbreviated level
  // 4 - Core level     
  // 5 - Partial (preliminary) level
  // 7 - Minimal level
  // 8 - Prepublication level
  // u - Unknown
  // z - Not applicable 
  val encodingLevel: String,  
  /* Possible values for descripting cataloging form */    
  // # - Non-ISBD
  // a - AACR 2
  // c - ISBD punctuation omitted     
  // i - ISBD punctuation included
  // u - Unknown 
  val descriptingCatalogingForm: String,  
  /* Possible values for multipart resource record level */  
  // # - Not specified or not applicable
  // a - Set     
  // b - Part with independent title
  // c - Part with dependent title 
  val multipartResourceRecordLevel: String,
  val mapEntry: Int) {
  //var value = new StringBuilder(" " * Marc.LEADER_LENGTH).replace(10, 11, "22").replace(20, 23, "4500").toString

  //override def toString = value

  /*def recordLength: String = value.slice(0, 5)
  def recordStatus: String = value.slice(5, 5)
  def typeOfRecord: String = value.slice(6, 6)
  def characterCoding: String = value.slice(9, 9)
  def baseAddress: String = value.slice(12, 16)
  def entryMap: String = value.slice(Marc.ENTRY_MAP_START, Marc.ENTRY_MAP_START + Marc.ENTRY_MAP_LENGTH - 1)*/

  /*def setRecordLength(length: String): Unit = {
    value = new StringBuilder(value).replace(0, 3, length).toString
  }*/
  
  def value = 
    "%05d".format(recordLength) +
    recordStatus +
    recordType + 
    bibliographicLevel +
    typeOfControl +
    codingScheme +
    indicatorCount.toString +
    subfieldCodeCount.toString +
    "%05d".format(baseAddress) +
    encodingLevel +
    descriptingCatalogingForm +
    multipartResourceRecordLevel +
    "%04d".format(mapEntry)

  def toXML: Elem =
    <leader>{value}</leader>
}
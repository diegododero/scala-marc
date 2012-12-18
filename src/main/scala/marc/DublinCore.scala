package marc

import scala.collection.mutable.Map
import exceptions.UndefinedFieldException
import scala.collection.mutable.MutableList

/**
 * A class for mapping MARC records to Dublin Core unqualified
 * Mapping used taken from:
 * http://www.loc.gov/marc/marc2dc.html
 * 
 * @author diego
 *
 */
class DublinCore(record: Record) {
  private val definedFields = Set("title", "date", "type", "creator", "subject", "description", "format", "identifier", "source", "language", "relation", "coverage", "rights")
  val fields = Map[String, MutableList[String]]()

  fields += "title" -> MutableList(record("245").get('a').get.value)
  fields += "date" -> MutableList(record("260").get('c').get.value)
  record.getFieldsByTag("655").map(f => 
      fields.update("type", getSubfields("type", f))
  )
  record.getFieldsByTags(Set("100", "110", "111", "700", "710", "711", "720")).map(f => 
      fields.update("creator", getSubfields("creator", f))
  )
  record.getFieldsByTags(Set("600", "610", "611", "630", "650", "653")).map(f => 
      fields.update("subject", getSubfields("subject", f))
  )
  record.getFieldsByTags((500 to 599).map(_ toString).toSet.diff(Set("506", "530", "540", "546"))).map(f => 
      fields.update("description", getSubfields("description", f))
  )
  record.getFieldsByTag("856").map(f => 
      fields += "format" -> f.subfields.filter(s => s.code == 'q').map(s => s.value)
  )  
  record.getFieldsByTag("856").map(f => 
      fields += "identifier" -> f.subfields.filter(s => s.code == 'u').map(s => s.value)
  )
  record.getFieldsByTag("786").map(f => 
      fields += "source" -> f.subfields.filter(s => s.code == 'o' || s.code == 't').map(s => s.value)
  )  
  record.getFieldsByTag("546").map(f => 
      fields += "language" -> f.subfields.map(s => s.value)
  )
  record.getFieldsByTag("530").map(f => 
      fields += "relation" -> f.subfields.map(s => s.value)
  )
  record.getFieldsByTags((760 to 787).map(_ toString).toSet).map(f => 
      fields.update("relation", getSubfields("relation", f))
  )
  record.getFieldsByTags(Set("651", "662", "751", "752")).map(f => 
      fields.update("coverage", getSubfields("coverage", f))
  )
  record.getFieldsByTags(Set("506", "540")).map(f => 
      fields.update("rights", getSubfields("rights", f))
  )  
  
  private def getSubfields(field: String, f: Field): MutableList[String] = fields.getOrElse(field, MutableList()) ++ List(f.subfields.map(s => s.value).mkString(" "))
  
  def get(field: String): MutableList[String] =
    if (definedFields.contains(field)){
      fields.getOrElse(field, MutableList())
    }
    else
    {
      throw new UndefinedFieldException(field)
    }
  
  override def toString = fields.map(f => f._2.map(d => f._1 + " " + d + "\n")).flatten.mkString

}
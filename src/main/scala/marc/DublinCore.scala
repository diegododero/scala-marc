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

  record.getFieldsByTag("245").map(f => 
      fields += "title" -> getSubfields(s => s.code == 'a', f)
  )  
  record.getFieldsByTag("260").map(f => 
      fields += "date" -> getSubfields(s => s.code == 'c', f)
  )  
  record.getFieldsByTag("655").map(f => 
      updateField("type", f)
  )
  record.getFieldsByTags(Set("100", "110", "111", "700", "710", "711", "720")).map(f => 
      updateField("creator", f)
  )
  record.getFieldsByTags(Set("600", "610", "611", "630", "650", "653")).map(f => 
      updateField("subject", f)
  )
  record.getFieldsByTags((500 to 599).map(_ toString).toSet.diff(Set("506", "530", "540", "546"))).map(f => 
      updateField("description", f)
  )
  record.getFieldsByTag("856").map(f => 
      fields += "format" -> getSubfields(s => s.code == 'q', f)
  )  
  record.getFieldsByTag("856").map(f => 
      fields += "identifier" -> getSubfields(s => s.code == 'u', f)
  )
  record.getFieldsByTag("786").map(f => 
      fields += "source" -> getSubfields(s => s.code == 'o' || s.code == 't', f)
  )  
  record.getFieldsByTag("546").map(f => 
      fields += "language" -> getSubfields(f)
  )
  record.getFieldsByTag("530").map(f => 
      fields += "relation" -> getSubfields(f)
  )
  record.getFieldsByTags((760 to 787).map(_ toString).toSet).map(f => 
      updateField("relation", f)
  )
  record.getFieldsByTags(Set("651", "662", "751", "752")).map(f => 
      updateField("coverage", f)
  )
  record.getFieldsByTags(Set("506", "540")).map(f => 
      updateField("rights", f)
  )  
  
  private def getSubfields(field: Field) = field.subfields.map(s => s.value)
  
  private def getSubfields(f: Subfield => Boolean, field: Field) = field.subfields.filter(f).map(s => s.value)
  
  private def updateField(field: String, f: Field): Unit = fields.update(field, getSubfields(field, f))
  
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
package marc2xml
import scala.xml.Elem
import marc.Field
import marc.Leader
import marc.Marc
import marc.Record

object Marc2XML {
  def convert(record: Record): Elem = 
    <record>
	 {convert(record.leader)}
</record>
    
  def convert(leader: Leader): Elem =
    <leader>{leader.toString}</leader>
  
  def convert(field: Field): Elem =
    if (field.tag.startsWith(Marc.CONTROL_TAG_PREFIX)){
      <controlfield>{field}</controlfield>
    }
    else{
    <datafield></datafield>
    }
}
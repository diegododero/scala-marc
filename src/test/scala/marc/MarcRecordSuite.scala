package test

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import marc._
import exceptions.InvalidLeaderFormatException

@RunWith(classOf[JUnitRunner])
class MarcRecordSuite extends FunSuite {
  trait TestRecord {
    val record = new Record(LeaderValidator.validate("01142cam  2200301 a 4500"))
  }

  test("record creation fields") {
    new TestRecord {
      assert(record.fields.size === 0)
    }
  }
  
  test("record leader validation") {
    new TestRecord {
      intercept[InvalidLeaderFormatException] {
        val leader: Leader = LeaderValidator.validate("01142eam  2200301 a 4500")
      }
    }
  }

  test("record add field") {
    new TestRecord {
      val field = new DataField("245", '1', '0')
      record.addField(field)
      assert(record.getFieldsByTag("245").size === 1)
    }
  }

  test("field apply size"){
    new TestRecord{
      val field = new DataField("245", '1', '0')
      field.addSubfield(new Subfield('a', "Proper title"))
      field.addSubfield(new Subfield('c', "Statement of responsibility"))    
      record.addField(field)
      assert(record("245").get.subfields.size === 2)
    }
  }  
  
  test("field apply content"){
    new TestRecord{
      val field = new DataField("245", '1', '0')
      val title = new Subfield('a', "Proper title")
      field.addSubfield(title)
      field.addSubfield(new Subfield('c', "Statement of responsibility"))     
      record.addField(field)    
      assert(record("245").get('a') === Some(title))
    }
  }

  test("record apply") {
    new TestRecord {
      val field = new DataField("245", '1', '0')
      record.addField(field)
      assert(record("245").size === 1)
    }
  }
}
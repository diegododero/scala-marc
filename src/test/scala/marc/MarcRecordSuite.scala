package test

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import marc._

@RunWith(classOf[JUnitRunner])
class MarcRecordSuite extends FunSuite {
  trait TestRecord{
    val record = new Record
  }
  
  test("record creation fields"){
    new TestRecord{
      assert(record.fields.size === 0)
    }
  }
  
  test("record creation leader"){
    new TestRecord{
      assert(record.leader.value === "          22        4500  ")
    }
  }
  
  test("record add field"){
    new TestRecord{
      val field = new DataField("245", '1', '0')
      record.addField(field)
      assert(record.getFieldsByTag("245").size === 1)
    }
  }
}
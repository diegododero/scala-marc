package test

import java.io._
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import marc._
import marc2xml.MarcXMLWriter
import io.MarcParser
import io.MarcReader
import exceptions.InvalidRecordLengthException

@RunWith(classOf[JUnitRunner])
class MarcReaderSuite extends FunSuite {
  trait TestMarc {
    val reader = new MarcReader("src/test/scala/marc/sandburg.mrc")
    val record = reader.next
    MarcXMLWriter.write("sandburg.xml", record)
  }

  test("leader") {
    new TestMarc {
      assert(record.leader.value === "01142cam  2200301 a 4500")
    }
  }
/*
  test("record size") {
    new TestMarc {
      assert(record.leader.recordLength.toInt === parser.recordSize)
    }
  }
*/
  test("record length less than 12 bytes") {
    new TestMarc {
      val r = new MarcReader("src/test/scala/marc/not_even_leader.mrc")
      intercept[InvalidRecordLengthException] {
        r.next
      }
    }
  }
  
  test("wrong record size in leader") {
    new TestMarc {
      val r = new MarcReader("src/test/scala/marc/wrong_length.mrc")
      intercept[InvalidRecordLengthException] {
        r.next
      }
    }
  }

  /*test("directory size"){
    new TestMarc{
      assert(record.directory.entries.size === 23)
    }
  }*/

  test("get fields by tag 650") {
    new TestMarc {
      assert(record.getFieldsByTag("650").size === 5)
    }
  }

  test("get control fields") {
    new TestMarc {
      assert(record.controlFields.size === 4)
    }
  }

  test("get data fields") {
    new TestMarc {
      assert(record.dataFields.size === 19)
    }
  }

  test("get subfields") {
    new TestMarc {
      assert(record.dataFields.collect { case f => f.subfields.toList }.flatten.size === 33)
    }
  }
}

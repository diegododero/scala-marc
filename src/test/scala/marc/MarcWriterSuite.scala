package test

import java.io._
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import marc._
import marc2xml.MarcXMLWriter
import io.MarcParser
import io.MarcReader
import io.MarcWriter
import exceptions.InvalidRecordLengthException

@RunWith(classOf[JUnitRunner])
class MarcWriterSuite extends FunSuite {
  trait TestMarc {
    val reader = new MarcReader("src/test/scala/marc/sandburg.mrc")
    val record = reader.next

  }
  
  test("writing") {
    new TestMarc {
      val writer = new MarcWriter("sandburg2.mrc")
      writer.write(record)
      writer.close
      val r = new MarcReader("sandburg2.mrc")
      val marc = r.next
      assert(marc.toTransmissionFormat === record.toTransmissionFormat)
    }
  }
}

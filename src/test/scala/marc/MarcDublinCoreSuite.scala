package marc

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.MarcReader

@RunWith(classOf[JUnitRunner])
class MarcDublinCoreSuite extends FunSuite {
  trait TestMarc {
    val reader = new MarcReader("src/test/scala/marc/sandburg.mrc")
    reader.start
    val record = reader.next
    val dc = record.toDublinCore
  }
  
  test("title") {
    new TestMarc {
      assert(dc.get("title") === List("Arithmetic /"))
    }
  }
  
  test("date") {
    new TestMarc {
      assert(dc.get("date") === List("c1993."))
    }
  }  
  
  test("creator") {
    new TestMarc {
      assert(dc.get("creator") === List("Rand, Ted, ill.", "Sandburg, Carl, 1878-1967."))
    }
  }    
  
  test("subject") {
    new TestMarc {
      assert(dc.get("subject") === List("Arithmetic Juvenile poetry.", "Children's poetry, American.", "Arithmetic Poetry.", "American poetry.", "Visual perception."))
    }
  }   

  test("description") {
    new TestMarc {
      assert(dc.get("description") === List("A poem about numbers and their characteristics. Features anamorphic, or distorted, drawings which can be restored to normal by viewing from a particular angle or by viewing the image's reflection in the provided Mylar cone.", "One Mylar sheet included in pocket."))
    }
  }   
}
package marc

import scala.collection.mutable.Map

class DublinCore(record: Record) {
  val fields = Map[String, List[String]]()

  fields += "title" -> List("")

}
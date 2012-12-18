package marc

import io.MarcReader

class ForgivingMarcReader(filename: String) extends MarcReader(filename: String) {
  override def getRecord(source: scala.io.Source): String = {
      val length = source.take(Marc.RECORD_LENGTH_SIZE).toList.mkString
      val rest = source.takeWhile(c => c != Marc.END_OF_RECORD).toList.mkString
      length ++ rest
  }
}
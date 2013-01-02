package io

import marc.Marc

/**
 * A class for reading MARC records from a file
 * It ignores the length defined in the leader
 * 
 * @author diego
 *
 */
class ForgivingMarcReader(filename: String) extends MarcReader(filename: String) {
  override def getRecord(source: scala.io.Source): String = {
     source.takeWhile(c => c != Marc.END_OF_RECORD).toList.mkString
  }
}
package io

import marc.Record
import java.io.PrintWriter
import java.io.File

class MarcWriter(val filename: String) {
  val writer = new PrintWriter(new File(filename))

  def write(marc: Record): Unit = {
    writer.write(marc.toTransmissionFormat)
  }
  
  def close: Unit =writer.close

}
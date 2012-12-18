package io

import marc.Record
import java.io.PrintWriter
import java.io.File

object MarcWriter {

  def write(marc: Record, filename: String): Unit = {
    val writer = new PrintWriter(new File(filename))
    writer.write(marc.toTransmissionFormat)
    writer.close
  }

}
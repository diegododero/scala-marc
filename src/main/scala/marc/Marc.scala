package marc

object Marc {
  val CONTROL_NUMBER_TAG_PREFIX = "001"
  val CONTROL_TAG_PREFIX = "00"
  val DIRECTORY_ENTRY_LENGTH = 12
  val END_OF_RECORD = 0x1D.toChar
  val ENTRY_MAP_START = 20
  val ENTRY_MAP_LENGTH = 4
  val ENTRY_TAG_FIELD_START = 0
  val ENTRY_TAG_FIELD_LENGTH = 3
  val FIELD_DELIMITER = 0x1E.toChar
  val LEADER_LENGTH = 24
  val SUBFIELD_DELIMITER = 0x1F.toChar
  val SUBJECT_FIELD_TAG_PREFIX = "6"
}
package exceptions

import marc.Marc

class InvalidDirectoryLenghtException extends ScalaMarcException {
  override def getMessage = "Directory's length should be multiple of " + Marc.DIRECTORY_ENTRY_LENGTH 
}
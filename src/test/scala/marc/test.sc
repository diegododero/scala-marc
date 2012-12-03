package marc

import java.io._
import marc._
import io._
import marc2xml._

object test {
	val subfields = scala.collection.mutable.Map[Char, String]()
                                                  //> subfields  : scala.collection.mutable.Map[Char,String] = Map()
  val title = new DataField("245", '1', '0', subfields)
                                                  //> title  : marc.DataField = 
  title.addSubfield('a', "Proper title")
  title                                           //> res0: marc.DataField = 245 10 a Proper title
  title.toXML                                     //> res1: scala.xml.Elem = <datafield ind1="1" tag="245" ind2="0">
                                                  //| 		<subfield code="a">Proper title</subfield>
                                                  //| 	</datafield>
  new File(".").getAbsolutePath()                 //> res2: java.lang.String = /home/diego/programas/eclipse/.
  val parser = new MarcParser("workspace/scalamarc/src/test/sandburg.mrc")
                                                  //> parser  : io.MarcParser = io.MarcParser@65b60280
  val leaderString = parser.byteArray.slice(0, 24).map(_ toChar).mkString("")
                                                  //> leaderString  : String = 01142cam  2200301 a 4500
  val leader = new Leader(leaderString)           //> leader  : marc.Leader = 01142cam  2200301 a 4500
  leader.recordLength                             //> res3: String = 0114
  leader.recordStatus                             //> res4: String = ""
  leader.characterCoding                          //> res5: String = ""
  leader.baseAddress                              //> res6: String = 0030
  leader.entryMap                                 //> res7: String = 450
  parser.directory.map(_ toChar).mkString("")     //> res8: String = 0010013000000030004000130050017000170080041000340100017000750
                                                  //| 2000250009204000180011704200090013505000260014408200160017010000320018624500
                                                  //| 8600218250001200304260005200316300004900368500004000417520022800457650003300
                                                  //| 685650003300718650002400751650002100775650002300796700002100819
  val entries = parser.directory.map(_ toChar).mkString("").grouped(12).toList
                                                  //> entries  : List[String] = List(001001300000, 003000400013, 005001700017, 008
                                                  //| 004100034, 010001700075, 020002500092, 040001800117, 042000900135, 050002600
                                                  //| 144, 082001600170, 100003200186, 245008600218, 250001200304, 260005200316, 3
                                                  //| 00004900368, 500004000417, 520022800457, 650003300685, 650003300718, 6500024
                                                  //| 00751, 650002100775, 650002300796, 700002100819)
  val first = entries.head                        //> first  : String = 001001300000
  val d = new Directory                           //> d  : marc.Directory = 
  d.addEntry(new Entry(first.slice(0, 3), first.slice(3, 7), first.slice(7, 12)))
                                                  //> res9: scala.collection.mutable.MutableList[marc.Entry] = MutableList(001 001
                                                  //| 3 00000)
  val record = parser.parse                       //> record  : marc.Record = Leader
                                                  //| 01142cam  2200301 a 4500
                                                  //| Directory 
                                                  //| 001 0013 00000
                                                  //| 003 0004 00013
                                                  //| 005 0017 00017
                                                  //| 008 0041 00034
                                                  //| 010 0017 00075
                                                  //| 020 0025 00092
                                                  //| 040 0018 00117
                                                  //| 042 0009 00135
                                                  //| 050 0026 00144
                                                  //| 082 0016 00170
                                                  //| 100 0032 00186
                                                  //| 245 0086 00218
                                                  //| 250 0012 00304
                                                  //| 260 0052 00316
                                                  //| 300 0049 00368
                                                  //| 500 0040 00417
                                                  //| 520 0228 00457
                                                  //| 650 0033 00685
                                                  //| 650 0033 00718
                                                  //| 650 0024 00751
                                                  //| 650 0021 00775
                                                  //| 650 0023 00796
                                                  //| 700 0021 00819
                                                  //| Fields 
                                                  //| 001    92005291 
                                                  //| 003 DLC
                                                  //| 005 19930521155141.9
                                                  //| 008 920219s1993    caua   j      000 0 eng  
                                                  //| 010    a    92005291 
                                                  //| 020    a 0152038655 :
                                                  //| 020    c $15.95
                                                  //| 040    a DLC
                                                  //| 040    c DLC
                                                  //| 040    d DLC
                                                  //| 042    a lcac
                                                  //| 050 00 a PS3537.A618
                                                  //| 050 00 b A88 1993
                                                  //| 082 00 2 20
                                                  //| 082 00 a 811/.52
                                                  //| 100 1  a Sandburg, Carl,
                                                  //| 100 1  d 1878-1967.
                                                  //| 245 10 a Arithmetic /
                                                  //| 245 10 c Carl Sandburg ; illustrated as an anamorphic adv
                                                  //| Output exceeds cutoff limit.
  parser.createDirectory.entries                  //> res10: scala.collection.mutable.MutableList[marc.Entry] = MutableList(001 00
                                                  //| 13 00000, 003 0004 00013, 005 0017 00017, 008 0041 00034, 010 0017 00075, 02
                                                  //| 0 0025 00092, 040 0018 00117, 042 0009 00135, 050 0026 00144, 082 0016 00170
                                                  //| , 100 0032 00186, 245 0086 00218, 250 0012 00304, 260 0052 00316, 300 0049 0
                                                  //| 0368, 500 0040 00417, 520 0228 00457, 650 0033 00685, 650 0033 00718, 650 00
                                                  //| 24 00751, 650 0021 00775, 650 0023 00796, 700 0021 00819)
  record.directory                                //> res11: marc.Directory = 001 0013 00000
                                                  //| 003 0004 00013
                                                  //| 005 0017 00017
                                                  //| 008 0041 00034
                                                  //| 010 0017 00075
                                                  //| 020 0025 00092
                                                  //| 040 0018 00117
                                                  //| 042 0009 00135
                                                  //| 050 0026 00144
                                                  //| 082 0016 00170
                                                  //| 100 0032 00186
                                                  //| 245 0086 00218
                                                  //| 250 0012 00304
                                                  //| 260 0052 00316
                                                  //| 300 0049 00368
                                                  //| 500 0040 00417
                                                  //| 520 0228 00457
                                                  //| 650 0033 00685
                                                  //| 650 0033 00718
                                                  //| 650 0024 00751
                                                  //| 650 0021 00775
                                                  //| 650 0023 00796
                                                  //| 700 0021 00819
  parser.getDataFields.mkString("\n")             //> res12: String = "   92005291 
                                                  //| DLC
                                                  //| 19930521155141.9
                                                  //| 920219s1993    caua   j      000 0 eng  
                                                  //|   a   92005291 
                                                  //|   a0152038655 :c$15.95
                                                  //|   aDLCcDLCdDLC
                                                  //|   alcac
                                                  //| 00aPS3537.A618bA88 1993
                                                  //| 00a811/.52220
                                                  //| 1 aSandburg, Carl,d1878-1967.
                                                  //| 10aArithmetic /cCarl Sandburg ; illustrated as an anamorphic adventure by 
                                                  //| Ted Rand.
                                                  //|   a1st ed.
                                                  //|   aSan Diego :bHarcourt Brace Jovanovich,cc1993.
                                                  //|   a1 v. (unpaged) :bill. (some col.) ;c26 cm.
                                                  //|   aOne Mylar sheet included in pocket.
                                                  //|   aA poem about numbers and their characteristics. Features anamorphic, or 
                                                  //| distorted, drawings which can be restored to normal by viewing from a partic
                                                  //| ular angle or by viewing the image's reflection in the provided Mylar cone.
                                                  //|  0aArithmeticxJuvenile poetry.
                                                  //|  0aChildren's poetry, American.
                                                  //|  1aArithmeticxPoetry.
                                                  //|  1aAmerican poetry.
                                                  //|  1aVisual perception.
                                                  //| 1 aRand, Ted,eill."
  val subs = parser.mergeDirectoryWithFields      //> subs  : scala.collection.mutable.MutableList[(marc.Entry, String)] = Mutabl
                                                  //| eList((001 0013 00000,"   92005291 "), (003 0004 00013,DLC), (005 0017 0001
                                                  //| 7,19930521155141.9), (008 0041 00034,"920219s1993    caua   j      000 0 en
                                                  //| g  "), (010 0017 00075,"  a   92005291 "), (020 0025 00092,"  a0152038655
                                                  //|  :c$15.95"), (040 0018 00117,"  aDLCcDLCdDLC"), (042 0009 00135,"  alc
                                                  //| ac"), (050 0026 00144,00aPS3537.A618bA88 1993), (082 0016 00170,00a811/.
                                                  //| 52220), (100 0032 00186,1 aSandburg, Carl,d1878-1967.), (245 0086 00218,
                                                  //| 10aArithmetic /cCarl Sandburg ; illustrated as an anamorphic adventure by
                                                  //|  Ted Rand.), (250 0012 00304,"  a1st ed."), (260 0052 00316,"  aSan Diego
                                                  //|  :bHarcourt Brace Jovanovich,cc1993."), (300 0049 00368,"  a1 v. (unpage
                                                  //| d) :bill. (some col.) ;c26 cm."), (500 0040 00417,"  aOne Mylar sheet in
                                                  //| cluded in pocket."), (520 0228 00457,"  aA poem about numbers and their ch
                                                  //| aracteristics. Features
                                                  //| Output exceeds cutoff limit.
  subs.get(13).get._2.split(Marc.SUBFIELD_DELIMITER)
                                                  //> res13: Array[String] = Array("  ", aSan Diego :, bHarcourt Brace Jovanovich
                                                  //| ,, cc1993.)
	parser.getFields.mkString("\n")           //> res14: String = 001    92005291 
                                                  //| 003 DLC
                                                  //| 005 19930521155141.9
                                                  //| 008 920219s1993    caua   j      000 0 eng  
                                                  //| 010    a    92005291 
                                                  //| 020    a 0152038655 :
                                                  //| 020    c $15.95
                                                  //| 040    a DLC
                                                  //| 040    c DLC
                                                  //| 040    d DLC
                                                  //| 042    a lcac
                                                  //| 050 00 a PS3537.A618
                                                  //| 050 00 b A88 1993
                                                  //| 082 00 2 20
                                                  //| 082 00 a 811/.52
                                                  //| 100 1  a Sandburg, Carl,
                                                  //| 100 1  d 1878-1967.
                                                  //| 245 10 a Arithmetic /
                                                  //| 245 10 c Carl Sandburg ; illustrated as an anamorphic adventure by Ted Rand
                                                  //| .
                                                  //| 250    a 1st ed.
                                                  //| 260    a San Diego :
                                                  //| 260    b Harcourt Brace Jovanovich,
                                                  //| 260    c c1993.
                                                  //| 300    a 1 v. (unpaged) :
                                                  //| 300    b ill. (some col.) ;
                                                  //| 300    c 26 cm.
                                                  //| 500    a One Mylar sheet included in pocket.
                                                  //| 520    a A poem about numbers and their characteristics. Features anamorphi
                                                  //| c, or distorted, drawings which can be restored to normal by viewing from a
                                                  //|  particular angle or by viewing the image's reflection 
                                                  //| Output exceeds cutoff limit.
	record                                    //> res15: marc.Record = Leader
                                                  //| 01142cam  2200301 a 4500
                                                  //| Directory 
                                                  //| 001 0013 00000
                                                  //| 003 0004 00013
                                                  //| 005 0017 00017
                                                  //| 008 0041 00034
                                                  //| 010 0017 00075
                                                  //| 020 0025 00092
                                                  //| 040 0018 00117
                                                  //| 042 0009 00135
                                                  //| 050 0026 00144
                                                  //| 082 0016 00170
                                                  //| 100 0032 00186
                                                  //| 245 0086 00218
                                                  //| 250 0012 00304
                                                  //| 260 0052 00316
                                                  //| 300 0049 00368
                                                  //| 500 0040 00417
                                                  //| 520 0228 00457
                                                  //| 650 0033 00685
                                                  //| 650 0033 00718
                                                  //| 650 0024 00751
                                                  //| 650 0021 00775
                                                  //| 650 0023 00796
                                                  //| 700 0021 00819
                                                  //| Fields 
                                                  //| 001    92005291 
                                                  //| 003 DLC
                                                  //| 005 19930521155141.9
                                                  //| 008 920219s1993    caua   j      000 0 eng  
                                                  //| 010    a    92005291 
                                                  //| 020    a 0152038655 :
                                                  //| 020    c $15.95
                                                  //| 040    a DLC
                                                  //| 040    c DLC
                                                  //| 040    d DLC
                                                  //| 042    a lcac
                                                  //| 050 00 a PS3537.A618
                                                  //| 050 00 b A88 1993
                                                  //| 082 00 2 20
                                                  //| 082 00 a 811/.52
                                                  //| 100 1  a Sandburg, Carl,
                                                  //| 100 1  d 1878-1967.
                                                  //| 245 10 a Arithmetic /
                                                  //| 245 10 c Carl 
                                                  //| Output exceeds cutoff limit.
}
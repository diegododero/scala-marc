package marc2xml

import scala.xml.PrettyPrinter
import scala.xml.Node
import scala.xml.NamespaceBinding
import scala.xml.MetaData

class MarcPrettyPrinter(val width: Int, val step: Int) extends PrettyPrinter(width, step) {

  override protected def leafTag(n: Node) = {
    def mkLeaf(sb: StringBuilder) {
      sb append '<'
      n nameToString sb
      formatAttributes(n, sb)
      sb append "/>"
    }
    val sb = new StringBuilder
    mkLeaf(sb)
    sb.toString
  }
    
  override protected def startTag(n: Node, pscope: NamespaceBinding): (String, Int) = {
    var i = 0
    def mkStart(sb: StringBuilder) {
      sb append '<'
      n nameToString sb
      i = sb.length + 1
      formatAttributes(n, sb)
      n.scope.buildString(sb, pscope)
      sb append '>'
    }
    val sb = new StringBuilder
    mkStart(sb)    
    (sb.toString, i)
  }
  
  private def compare(attribute1: MetaData, attribute2: MetaData): Boolean = {
    val values = List("tag", "ind1", "ind2")
    values.indexOf(attribute1.key) < values.indexOf(attribute2.key)
  }
  
  protected def formatAttributes(n: Node, sb: StringBuilder) {
    if (n.attributes.toList.size == 3){
      //Ugly: XML attributes should not have an order
    	n.attributes.toList.sortWith(compare).foldLeft(sb) { (a,b) => a.append(' '); b.toString1(a); a
    	}}
    else  {
    	n.attributes.toList.reverse.foldLeft(sb) { (a,b) => a.append(' '); b.toString1(a); a }}
    //(sb /: n.attributes.toList.reverse) { (r,v) => r.append(' '); v.toString1(r); r }
  }
  
}
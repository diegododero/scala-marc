package marc

import exceptions.InvalidLeaderFormatException

object LeaderValidator {
    def validate(data: String): Leader = {
    /* This should be the regular expression */
    /* (\d{5})([nc])([ac])([acmbds])([#s\s])([\s])(\d{1})(\d{1})(\d{5})([#1385u\s])([#iau\s])([#r\s])(\d{4}) */
    /* But there are fixed parts in the leader */        
    val Leader = """(\d{5})([acdnp])([a-gi])([acmbds])([#s\s])([#a\s])(2)(2)(\d{5})([#1-578u\s])([#iau\s])([#r\s])(4500)""".r
    data match {
      case Leader(l, rs, rt, bl, tc, cs, ic, sc, ba, el, dcf, mrrl, me) => new Leader(l.toInt, rs, rt, bl, tc, cs, ic.toInt, sc.toInt, ba.toInt, el, dcf, mrrl, me.toInt)
      case _ => throw new InvalidLeaderFormatException(data)
    }
  }
}
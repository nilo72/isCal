package ical

object Config{
  final val DE_DE:String = "DE_DE"
  val DEFAULT_LANGUAGE:String = DE_DE
  val CRLF:String = "\r\n"
}

/**
 * Base for all Informations to transport in a Calendar
 * contentline        = name *(";" param ) ":" value CRLF
 */

trait Contentline{
  val name:String
  val param:List[String]
  val value:Any
  
  override def toString = {
    val sb = StringBuilder.newBuilder
    sb.append(name)
    if(!param.isEmpty) sb.append((for(c <- param ; val x = ";" +c ) yield x) mkString) 
    sb.append(":")
    sb.append(value)
    sb.append("\n")
    sb.toString
  }
}

object ICalendarCore {
  case class BeginCL(name:String="BEGIN",value:String,param:List[String]=List()) extends Contentline{}
  case class EndCL(name:String="END",value:String,param:List[String]=List()) extends Contentline{}
}

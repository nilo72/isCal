package ical

object Config{
  final val DE_DE:String = "DE_DE"
  val DEFAULT_LANGUAGE:String = DE_DE
}

trait Eventprop



/**
 * Base for all Informations to transport in a Calendar
 * contentline        = name *(";" param ) ":" value CRLF
 */

trait Contentline{
  val name:String
  val param:List[String]
  val value:String
  
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

case class BeginCL(name:String="BEGIN",value:String,param:List[String]=List()) extends Contentline{}
case class EndCL(name:String="END",value:String,param:List[String]=List()) extends Contentline{}



/**
 * Implementation of Property page 50 (4.5)
 */

case class Property(param:List[String]=List(),value: String,name:String) extends Contentline


case class ToDoProp()
case class Alarmc()
 
case class Created(name:String="CREATED",value:String,param:List[String]=List()) extends Contentline with Eventprop
/**
 * Imlementation of Location on page 84 (4.8.1.7)
 * missing param implementation (altrepparam , languageparam)
 */
case class Organizer(name:String="ORGANIZER",value:String,param:List[String]=List()) extends Contentline with Eventprop


/**
 * calprops on page 50 (4.6)
 * prodid and version are required
 * calscale and method are optional <- Not implemented
 */
case class Calprops(props:List[Contentline]=List(ProdID(List()),Version(List()))){
  override def toString():String = props.mkString
}

case class Component()
/**
 * page 74 (4.7.3)
 */
case class ProdID(param:List[String],value:String="-//isCal tool//Testframe//DE",name:String="PRODID") extends Contentline{}
/**
 * page 75 (4.7.4)
 */
case class Version(param:List[String],value:String="2.0",name:String="VERSION") extends Contentline
/**
 * Implementation of language parameter. value can only be RFC1766 defined languagedefinitionsh
 */
case class LanguageParam(final val name:String="LANGUAGE",value:String=Config.DEFAULT_LANGUAGE,param:List[String]=List()) extends Contentline
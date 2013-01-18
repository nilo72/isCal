package ical

trait Eventprop
trait CalendarComponent{
  val desc:String
  val defaultProps = List(Class)
}
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
    if(!param.isEmpty){
      sb.append(";")
      sb.append(param)
    } 
    sb.append(":")
    sb.append(value)
    sb.append("\n")
    sb.toString
  }
}

case class BeginCL(name:String="BEGIN",value:String,param:List[String]=List()) extends Contentline{}
case class EndCL(name:String="END",value:String,param:List[String]=List()) extends Contentline{}

/**
 * Implementation of iCalendat Object RFC2445 page 49 (4.4)
 */
case class ICalobject(body:ICalbody){
  val desc = "VCALENDAR"
  override def toString():String = BeginCL(value=desc) +  body.toString + EndCL(value=desc)
}

/**
 * Implementation of Property page 50 (4.5)
 */

case class Property(param:List[String]=List(),value: String,name:String) extends Contentline

/**
 * Calendar Components page 50 (4.6)
 * VEVENT (4.6.1)
 * different datastructures for defaults and 'MUST NOT' ' BUT ONLY ONE' etc. function for merging together in eventprops
 * take care of overwriting Class= Public overwirtten by paramList Class=Private f.e.
 */
case class VEvent(eventprops:List[Eventprop]) extends CalendarComponent{
  val desc = "VEVENT"
  override def toString():String = BeginCL(value=desc) + 
  								   eventprops.mkString +
  								   EndCL(value=desc)  
}

case class VToDo(todoprop:ToDoProp, alarm:Alarmc) extends CalendarComponent{
  val desc = "VTODO"
  override def toString():String = BeginCL(value=desc) + todoprop.toString + EndCL(value=desc)
}
case class VJournal() extends CalendarComponent{
  val desc = "VJOURNAL"
}
case class VFreeBusy() extends CalendarComponent{
  val desc = "VFREEBUSY"
}
case class VTimezone() extends CalendarComponent{
  val desc = "VTIMEZONE"
}

case class ToDoProp()
case class Alarmc()
/**
 * Implementation of class page 79 (4.8.1.3)
 * can only become "PRIVATE","PUBLIC","CONFIDENTIAL"
 */
case class Class(name:String="CLASS",value:String="PUBLIC",param:List[String]=List()) extends Contentline with Eventprop{
  
} 
case class Created(name:String="CREATED",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Description(name:String="DESCRIPTION",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Dtstart(name:String="DTSTART",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Geo(name:String="GEO",value:String,param:List[String]=List()) extends Contentline with Eventprop

case class Location(name:String="LOCATION",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Organizer(name:String="ORGANIZER",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Status(name:String="STATUS",value:String,param:List[String]=List()) extends Contentline with Eventprop


/**
 * icalbody on page 50 (4.6)
 */
case class ICalbody(comp: List[CalendarComponent],props: Calprops=Calprops()){
  override def toString():String = props.toString + comp.mkString
}
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

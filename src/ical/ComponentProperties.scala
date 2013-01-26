package ical

object ComponentProperties {
/**
 * Implementation of class page 79 (4.8.1.3)
 * can only become "PRIVATE","PUBLIC","CONFIDENTIAL"
 */
case class Class(name:String="CLASS",value:String="PUBLIC",param:List[String]=List()) extends Contentline with Eventprop{
  val allowedValues = List("PRIVATE","PUBLIC","CONFIDENTIAL")
  require(allowedValues.contains(value),{println(s"Value: >$value< is not allowed!")})
}
case class Description(name:String="DESCRIPTION",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Dtstart(name:String="DTSTART",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Geo(name:String="GEO",value:String,param:List[String]=List()) extends Contentline with Eventprop
/**
 * Imlementation of Location on page 84 (4.8.1.7)
 * missing param implementation (altrepparam , languageparam)
 */

case class Location(name:String="LOCATION",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Status(name:String="STATUS",value:String,param:List[String]=List()) extends Contentline with Eventprop

case class Created(name:String="CREATED",value:String,param:List[String]=List()) extends Contentline with Eventprop
case class Organizer(name:String="ORGANIZER",value:String,param:List[String]=List()) extends Contentline with Eventprop

case class ToDoProp()
}
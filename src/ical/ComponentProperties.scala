package ical

object ComponentProperties {
  
  trait EventProp
  trait ToDoProp
  
  trait Calprop
  /**
   * Implementation of class page 79 (4.8.1.3)
   * can only become "PRIVATE","PUBLIC","CONFIDENTIAL"
   */
  case class Class(name: String = "CLASS", value: String = "PUBLIC", param: List[String] = List()) extends Contentline with EventProp with ToDoProp{
    val allowedValues = List("PRIVATE", "PUBLIC", "CONFIDENTIAL")
    require(allowedValues.contains(value), { println(s"Value: >$value< is not allowed!") })
  }

  case class Description(name: String = "DESCRIPTION", value: String, param: List[String] = List()) extends Contentline with EventProp
  case class Geo(name: String = "GEO", value: String, param: List[String] = List()) extends Contentline with EventProp
  /**
   * Imlementation of Location on page 84 (4.8.1.7)
   * missing param implementation (altrepparam , languageparam)
   */

  case class Location(name: String = "LOCATION", value: String, param: List[String] = List()) extends Contentline with EventProp
  case class Status(name: String = "STATUS", value: String, param: List[String] = List()) extends Contentline with EventProp

  case class Created(name: String = "CREATED", value: String, param: List[String] = List()) extends Contentline with EventProp
  case class Organizer(name: String = "ORGANIZER", value: String, param: List[String] = List()) extends Contentline with EventProp
/**
 *	DTSTART Implementing 4.8.2.4 Date/Time Start page 93 ff
 * 
 */
  import ical.PropertyParameters.PropertyParam
  import ical.PropertyParameters.TZIdParam
  import ical.ValueTypes.TimeValueType
  case class DTEnd(name: String="DTEND", value: TimeValueType, param:List[PropertyParam]=List(TZIdParam())) extends Contentline with EventProp{
    override def toString = name + param.mkString + ":" + value.toString + Config.CRLF    
  }
  case class DTStart(name: String = "DTSTART", value: TimeValueType, param:List[PropertyParam]=List(TZIdParam())) extends Contentline with EventProp{
    //  default value type = DATE_TIME
    override def toString = name + (param  mkString) + ":" + value.toString + Config.CRLF
  }
}

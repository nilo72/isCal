package ical

object ComponentProperties {

  trait EventProp extends ContentLine

  trait EventPropMNOMTO extends EventProp

  trait EventPropMOMTO extends EventProp

  trait EventPropMNOISEV extends EventProp

  trait ToDoProp

  trait Calprop

  /**
   * Implementation of class page 79 (4.8.1.3)
   * can only become "PRIVATE","PUBLIC","CONFIDENTIAL"
   */
  case class Class(name: String = "CLASS", value: String = "PUBLIC", param: List[String] = List()) extends EventPropMNOMTO with ToDoProp {
    val allowedValues = List("PRIVATE", "PUBLIC", "CONFIDENTIAL")
    require(allowedValues.contains(value), {
      println(s"Value: >$value< is not allowed!")
    })
  }

  case class Description(name: String = "DESCRIPTION", value: String, param: List[String] = List()) extends EventPropMNOMTO

  case class Geo(name: String = "GEO", value: String, param: List[String] = List()) extends EventPropMNOMTO

  /**
   * Imlementation of Location on page 84 (4.8.1.7)
   * missing param implementation (altrepparam , languageparam)
   */

  case class Location(name: String = "LOCATION", value: String, param: List[String] = List()) extends ContentLine with EventPropMNOMTO

  case class Status(name: String = "STATUS", value: String, param: List[String] = List()) extends ContentLine with EventPropMNOMTO

  case class Created(name: String = "CREATED", value: String, param: List[String] = List()) extends ContentLine with EventPropMNOMTO

  case class Organizer(name: String = "ORGANIZER", value: String, param: List[String] = List()) extends ContentLine with EventPropMNOMTO

  case class Summary(name: String = "SUMMARY", value: String, param: List[String] = List()) extends ContentLine with EventPropMNOMTO

  case class TimeZoneID(final val name: String = "TZID", value: String = Config.DEFAULT_TIMEZONE, param: List[String] = List()) extends ContentLine with EventProp

  /**
   * DTSTART Implementing 4.8.2.4 Date/Time Start page 93 ff
   *
   */

  import ical.PropertyParameters.PropertyParam
  import ical.PropertyParameters.TZIdParam
  import ical.ValueTypes.TimeValueType

  trait DT {
    def name: String

    def value: TimeValueType

    def param: List[PropertyParam]

    override def toString = name + (param mkString) + ":" + value.toString + Config.CRLF
  }

  case class DTStart(name: String = "DTSTART", value: TimeValueType, param: List[PropertyParam] = List(TZIdParam())) extends DT with EventPropMNOMTO

  case class DTEnd(name: String = "DTEND", value: TimeValueType, param: List[PropertyParam] = List(TZIdParam())) extends DT with EventPropMNOISEV

  case class Duration(name: String = "DURATION", value: TimeValueType, param: List[PropertyParam] = List()) extends EventPropMNOISEV

  //  default value type = DATE_TIME
}

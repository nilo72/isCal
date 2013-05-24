package ical

import ComponentProperties._
import ical.ICalendarCore._

object CalendarComponents {

  trait CalendarComponent {
    val desc: String

    def occurOnceProps: Set[EventPropMNOMTO]

    def occurMultipleProps: List[EventProp]

    def occurExcludeProps: EventProp

    def mergedProps: List[EventProp] = occurExcludeProps :: (occurOnceProps toList) ++ occurMultipleProps

    override def toString(): String = BeginCL(value = desc) + (mergedProps mkString) + EndCL(value = desc)
  }

  /**
   * Calendar Components page 50 (4.6)
   * VEVENT (4.6.1)
   * different datastructures for defaults and 'MUST NOT' ' BUT ONLY ONE' etc. function for merging together in eventprops
   * take care of overwriting Class= Public overwirtten by paramList Class=Private f.e.
   */
  case class VEvent(eventprops: List[EventProp], alarmc: List[VAlarm]) extends CalendarComponent {

    val desc = "VEVENT"

    import scala.collection.immutable.HashSet

    override def occurOnceProps = eventprops.collect {
      case x: EventPropMNOMTO => x
    } toSet

    override def occurMultipleProps = eventprops.collect {
      case y: EventPropMOMTO => y
    }

    override def occurExcludeProps = (eventprops.collectFirst {
      case y: EventPropMNOISEV => y
    }) get
  }

  case class VToDo(todoprop: ToDoProp, alarm: VAlarm) extends CalendarComponent {
    override def occurExcludeProps = null

    override def occurOnceProps: Set[EventPropMNOMTO] = Set()

    override def occurMultipleProps = List()

    val desc = "VTODO"
  }

  case class VJournal() extends CalendarComponent {
    override def occurExcludeProps = null

    override def occurOnceProps: Set[EventPropMNOMTO] = Set()

    override def occurMultipleProps = List()

    val desc = "VJOURNAL"
  }

  case class VFreeBusy() extends CalendarComponent {
    override def occurExcludeProps = null

    def occurOnceProps: Set[EventPropMNOMTO] = Set()

    override def occurMultipleProps = List()

    val desc = "VFREEBUSY"
  }

  object VTimezone {

    import ical.PropertyParameters.TZIdParam
    import ical.DefaultProperty

    import ical.ComponentProperties.TimeZoneID

    val tzDayLightDefault = List(
      DefaultProperty("TZOFFSETFROM", "+0100", List()),
      DefaultProperty("TZOFFSETTO", "+0300", List()),
      DefaultProperty("TZNAME", "CEST", List()),
      DefaultProperty("DTSTART", "19700329T020000", List()),
      DefaultProperty("RRULE", "FREQ=YEARLY;INTERVAL=1;BYDAY=-1SU;BYMONTH=3", List()))

    val tzStandardDefault = List(
      DefaultProperty("TZOFFSETFROM", "+0200", List()),
      DefaultProperty("TZOFFSETTO", "+0100", List()),
      DefaultProperty("TZNAME", "CET", List()),
      DefaultProperty("DTSTART", "19701025T030000", List()),
      DefaultProperty("RRULE", "FREQ=YEARLY;INTERVAL=1;BYDAY=-1SU;BYMONTH=10", List()))

    case class Daylight(param: List[ContentLine]) extends CalendarComponent with ContentLine {
      override def occurExcludeProps = null

      override def occurMultipleProps = List()

      def occurOnceProps: Set[EventPropMNOMTO] = Set()

      val desc = "DAYLIGHT"
      val defaultProps = List()
      val name = ""
      val value = ""

      override def toString = BeginCL(value = desc) + (param mkString) + EndCL(value = desc)
    }

    case class Standard(param: List[ContentLine]) extends CalendarComponent with ContentLine {
      override def occurExcludeProps = null

      override def occurMultipleProps = List()

      def occurOnceProps: Set[EventPropMNOMTO] = Set()

      val desc = "STANDARD"
      val defaultProps = List()
      val name = ""
      val value = ""

      override def toString = BeginCL(value = desc) + (param mkString) + EndCL(value = desc)
    }

    case class VTimezone() extends CalendarComponent {
      override def occurExcludeProps = null

      override def occurMultipleProps = List()

      def occurOnceProps: Set[EventPropMNOMTO] = Set()

      val defaultProps = List(TimeZoneID(), DefaultProperty("X_LIC_LOCATION", "Europe/Berlin", List()), Daylight(tzDayLightDefault), Standard(tzStandardDefault))
      val desc = "VTIMEZONE"

      override def toString(): String = BeginCL(value = desc) + (defaultProps mkString) + EndCL(value = desc)
    }

  }

  case class VAlarm() extends CalendarComponent {
    override def occurExcludeProps = null

    def occurOnceProps: Set[EventPropMNOMTO] = Set()

    override def occurMultipleProps = List()

    val desc = "VALARM"

    override def toString(): String = BeginCL(value = desc) + Config.CRLF + EndCL(value = desc)
  }

}

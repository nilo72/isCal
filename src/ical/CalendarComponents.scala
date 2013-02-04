package ical

import ComponentProperties.Class
import ComponentProperties.ToDoProp
import ComponentProperties.EventProp
import ical.ICalendarCore._

object CalendarComponents {

  trait CalendarComponent{
    val desc:String
    def defaultProps:List[Contentline] // = List(Class())  
  }
  
  /**
   * Calendar Components page 50 (4.6)
   * VEVENT (4.6.1)
   * different datastructures for defaults and 'MUST NOT' ' BUT ONLY ONE' etc. function for merging together in eventprops
   * take care of overwriting Class= Public overwirtten by paramList Class=Private f.e.
   */
  case class VEvent(eventprops: List[EventProp],alarmc:List[VAlarm]) extends CalendarComponent {
    
    val desc = "VEVENT"
    val defaultProps = List()
    def mergedProps: List[EventProp] = List()

    
    
    override def toString(): String = BeginCL(value = desc) +
      eventprops.mkString +
      defaultProps.mkString +
      EndCL(value = desc)
  }

  case class VToDo(todoprop: ToDoProp, alarm: VAlarm) extends CalendarComponent {
    val desc = "VTODO"
    val defaultProps = List()
    override def toString(): String = BeginCL(value = desc) + todoprop.toString + EndCL(value = desc)
  }
  
  case class VJournal() extends CalendarComponent {
    val defaultProps = List()
    val desc = "VJOURNAL"
  }
  
  case class VFreeBusy() extends CalendarComponent {
    val defaultProps = List()
    val desc = "VFREEBUSY"
  }
  /**
   * BEGIN:VTIMEZONE
TZID:Europe/Berlin
X-LIC-LOCATION:Europe/Berlin
BEGIN:DAYLIGHT
TZOFFSETFROM:+0100
TZOFFSETTO:+0200
TZNAME:CEST
DTSTART:19700329T020000
RRULE:FREQ=YEARLY;INTERVAL=1;BYDAY=-1SU;BYMONTH=3
END:DAYLIGHT
BEGIN:STANDARD
TZOFFSETFROM:+0200
TZOFFSETTO:+0100
TZNAME:CET
DTSTART:19701025T030000
RRULE:FREQ=YEARLY;INTERVAL=1;BYDAY=-1SU;BYMONTH=10
END:STANDARD
END:VTIMEZONE
   */
  case class VTimezone() extends CalendarComponent {
    val defaultProps = List()
    val desc = "VTIMEZONE"
  }

  case class VAlarm() extends CalendarComponent{
    val defaultProps = List()
    val desc = "VALARM"
    override def toString(): String = BeginCL(value = desc) + Config.CRLF + EndCL(value = desc)
  }
  
}

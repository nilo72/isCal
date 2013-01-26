package ical

import ComponentProperties.Class
import ComponentProperties.ToDoProp
import ICalendarCore._

object CalendarComponents {

trait CalendarComponent{
  val desc:String
  val defaultProps = List(Class())  
}
  /**
   * Calendar Components page 50 (4.6)
   * VEVENT (4.6.1)
   * different datastructures for defaults and 'MUST NOT' ' BUT ONLY ONE' etc. function for merging together in eventprops
   * take care of overwriting Class= Public overwirtten by paramList Class=Private f.e.
   */
  case class VEvent(eventprops: List[Eventprop]) extends CalendarComponent {
    val desc = "VEVENT"
    def mergedProps: List[Eventprop] = List()
    override def toString(): String = BeginCL(value = desc) +
      eventprops.mkString +
      defaultProps.mkString +
      EndCL(value = desc)
  }

  case class VToDo(todoprop: ToDoProp, alarm: Alarmc) extends CalendarComponent {
    val desc = "VTODO"
    override def toString(): String = BeginCL(value = desc) + todoprop.toString + EndCL(value = desc)
  }
  case class VJournal() extends CalendarComponent {
    val desc = "VJOURNAL"
  }
  case class VFreeBusy() extends CalendarComponent {
    val desc = "VFREEBUSY"
  }
  case class VTimezone() extends CalendarComponent {
    val desc = "VTIMEZONE"
  }

case class Alarmc()
}
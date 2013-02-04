package ical

import CalendarComponents._
import ComponentProperties._
import ICalendar._
import ValueTypes._
import java.util.Calendar

object Main {

  def main(args: Array[String]): Unit = {
   val ct = Class(value="PRIVATE")
   val event = VEvent(List(Location(value="PC-Pool 11.01 a"),Organizer(value="Oliver Neumann"),Description(value="BAI1-PRP1"),DTStart(value=ICalDateTime(Calendar.getInstance)),DTEnd(value=ICalDateTime(Calendar.getInstance))),List())
   val event2 = VEvent(List(Location(value="PC-Pool 11.01 a"),Organizer(value="Oliver Neumann"),Description(value="BAI1-PRP1"),DTStart(value=ICalDateTime(Calendar.getInstance)),DTEnd(value=ICalDateTime(Calendar.getInstance))),List())
   val icalbody = ICalbody(List(event,event2))
   val cal = ICal(body=icalbody)
   println(cal.toString)
  }
}
package ical

import CalendarComponents._
import ComponentProperties._
import iCalendarObject._

object Main {

  def main(args: Array[String]): Unit = {
   val ct = Class(value="PRIVATE")
   val event = VEvent(List(Location(value="PC-Pool 11.01 a"),Organizer(value="Oliver Neumann"),Description(value="BAI1-PRP1")))
   val event2 = VEvent(List(Location(value="PC-Pool 11.01 a"),Organizer(value="Oliver Neumann"),Description(value="BAI1-PRP1")))
   val icalbody = ICalbody(List(event,event2))
   val cal = ICalobject(body=icalbody)
   println(cal.toString)
  }
}
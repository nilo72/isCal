import ical.CalendarComponents.VEvent
import ical.CalendarComponents.VEvent
import ical.CalendarComponents.{VEvent, VTimezone}
import ical.ComponentProperties._
import ical.ComponentProperties.Class
import ical.ComponentProperties.Description
import ical.ComponentProperties.DTEnd
import ical.ComponentProperties.DTStart
import ical.ComponentProperties.Location
import ical.ComponentProperties.Organizer
import ical.ICalendar.{ICal, ICalbody}
import ical.ValueTypes.ICalDateTime
import ical.ValueTypes.ICalDateTime
import java.util.Calendar

object Main {

  def main(args: Array[String]): Unit = {
    val ct = Class(value = "PRIVATE")
    val tz = VTimezone.VTimezone()
    val event = VEvent(List(Location(value = "PC-Pool 11.01 b"), Location(value = "PC-Pool 11.01 a"), Organizer(value = "Oliver Neumann"), Description(value = "BAI1-PRP1"), DTStart(value = ICalDateTime(Calendar.getInstance)), DTEnd(value = ICalDateTime(Calendar.getInstance))), List())
    val event2 = VEvent(List(Location(value = "PC-Pool 11.01 a"), Organizer(value = "Oliver Neumann"), Description(value = "BAI1-PRP1"), DTStart(value = ICalDateTime(Calendar.getInstance)), DTEnd(value = ICalDateTime(Calendar.getInstance))), List())
    val icalbody = ICalbody(List(tz, event, event2))
    val cal = ICal(body = icalbody)
    println(cal.toString)
  }
}
//package test

import ical.CalendarComponents.VEvent
import org.scalatest.FunSpec
import java.util.{TimeZone, Calendar}
import ical.ComponentProperties._
import ical.PropertyParameters._
import ical.ValueTypes._

class ComponentPropertiesTest extends FunSpec {

  trait TestSets {
    val t1 = VEvent(List(Class()), List())
  }

  //DTSTART;TZID=Europe/Berlin:20130206T023330
  val date = Calendar.getInstance
  date.set(Calendar.YEAR, 2013)
  date.set(Calendar.MONTH, Calendar.FEBRUARY)
  date.set(Calendar.DAY_OF_MONTH, 6)
  date.set(Calendar.HOUR_OF_DAY, 23)
  date.set(Calendar.MINUTE, 30)
  date.set(Calendar.AM_PM, Calendar.PM)
  date.setTimeZone(TimeZone.getTimeZone( """Europe/Berlin"""))
  describe("The ComponentPropertie DTStart") {
    it("should give a start time") {
      val dt = DTStart(value = ICalDateTime(date))
      println(dt.toString)
      assert("DTSTART;;TZID=Europe/Berlin:20130206T023330" === dt.toString)
    }
  }
}
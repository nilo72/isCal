//package test

import org.scalatest.FunSpec
import java.util.Calendar


import ical.ValueTypes.ICalDate


class ValueTypesTest extends FunSpec {
  describe("The value type DATE ") {
    it("should format a java.util.Calendar to a String representation of form year moth day") {
      val cal = Calendar.getInstance()
      cal.set(Calendar.YEAR, 2013)
      cal.set(Calendar.MONTH, Calendar.JANUARY)
      cal.set(Calendar.DATE, 15)
      val ical = ICalDate(cal)
      assert("20130115" === ical.toString)
      cal.set(Calendar.DATE, 30)
      val ical2 = ICalDate(cal)
      assert("20130130" === ical2.toString)
    }
  }
}
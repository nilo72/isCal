package test

import org.scalatest.FunSpec

import ical.CalendarComponents.VEvent
import ical.CalendarProperties.Version
import ical.CalendarProperties.ProdID

class VEventTest extends FunSpec {
  describe("Ein VEVENT "){
    it("soll default Werte Version und ProdID enthalten.") {
      val event = VEvent(List(),List())
      val v = Version(List())
      val p = ProdID(List())
      assert(event.defaultProps.contains(v))
      assert(event.defaultProps.contains(p))
    }
    it("darf Werte nur ein mal enthalten") {
      val p = Version(List())
      val event = VEvent(List(),List())
      
    }
  }
}
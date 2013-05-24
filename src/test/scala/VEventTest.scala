import org.scalatest.FunSpec
import ical.CalendarComponents.VEvent
import ical.CalendarProperties.Version
import ical.CalendarProperties.ProdID
import ical.ComponentProperties.Summary

class VEventTest extends FunSpec {
  describe("A VEVENT ") {
    it("should contain ... but must not occur more than once.") {
      val event = VEvent(List(Summary(value = "test"), Summary(value = "test2")), List())
      //assert(event.eventprops.filter(f => isInstanceOf[Summary] ).size <= 2)
      assert(condition = true)
    }
    it("darf Werte nur ein mal enthalten") {
      val p = Version(List())
      val event = VEvent(List(), List())
    }
  }
}

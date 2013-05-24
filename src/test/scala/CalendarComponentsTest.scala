import org.scalatest.FunSuite
import ical.CalendarComponents.VEvent
import ical.ComponentProperties.Class

class CalendarComponentsTest extends FunSuite {

  trait TestSets {
    val t1 = VEvent(List(Class()), List())
  }

  test("Create Event") {
    new TestSets {
      val event = VEvent(List(Class()), List())
      assert(event == t1, "")
    }
  }

  ignore("This Test") {

  }
}
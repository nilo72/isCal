package ical
import CalendarComponents.CalendarComponent
import ICalendarCore._
import CalendarProperties.ProdID
import CalendarProperties.Version

object iCalendarObject {
  /**
   * calprops on page 50 (4.6)
   * prodid and version are required
   * calscale and method are optional <- Not implemented
   */
  case class Calprops(props: List[Contentline] = List(ProdID(List()), Version(List()))) {
    override def toString(): String = props.mkString
  }

  /**
   * Implementation of Property page 50 (4.5)
   */

  case class Property(param: List[String] = List(), value: String, name: String) extends Contentline

  /**
   * Implementation of iCalendat Object RFC2445 page 49 (4.4)
   */
  case class ICalobject(body: ICalbody) {
    val desc = "VCALENDAR"
    override def toString(): String = BeginCL(value = desc) + body.toString + EndCL(value = desc)
  }
  /**
   * icalbody on page 50 (4.6)
   */
  case class ICalbody(comp: List[CalendarComponent], props: Calprops = Calprops()) {
    override def toString(): String = props.toString + comp.mkString
  }

}

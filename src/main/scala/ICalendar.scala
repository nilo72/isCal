package ical

import CalendarComponents.CalendarComponent
import ICalendarCore._
import CalendarProperties.ProdID
import CalendarProperties.Version
import CalendarProperties.CalProp

object ICalendar {

  /**
   * calprops on page 50 (4.6)
   * prodid and version are required
   * calscale and method are optional <- Not implemented
   */
  /**
   * Es müssen die optionalen, und die nötigen gemerged werden, wobei 
   */
  case class CalProps(props: Set[CalProp] = Set()) {
    val required = Set[CalProp](Version(List()), ProdID(List()), Version(List()))

    override def toString(): String = (required ++ props) mkString
  }

  /**
   * Implementation of iCalendat Object RFC2445 page 49 (4.4)
   */
  case class ICal(body: ICalbody) {
    val desc = "VCALENDAR"

    override def toString(): String = BeginCL(value = desc) + body.toString + EndCL(value = desc)
  }

  /**
   * icalbody on page 50 (4.6)
   */
  case class ICalbody(comp: List[CalendarComponent], props: CalProps = CalProps()) {
    override def toString(): String = props.toString + comp.mkString
  }

}

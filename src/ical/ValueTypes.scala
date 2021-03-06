package ical

import java.util.Date
import java.util.Calendar

object ValueTypes {

  trait ValueType
  
  /**
   * page 35 4.3.5
   */
  
  trait TimeValueType{
    def value:Calendar
    def prependZero(x:Int):String = x match {
      case y if y < 10 => "0" + y.toString
      case _  => x.toString
    }
    override def toString = year + month + day
    val year = value.get(Calendar.YEAR).toString
    val month = prependZero(value.get(Calendar.MONTH)+1) 
    val day = prependZero(value.get(Calendar.DAY_OF_MONTH))
  }
  
  case class ICalDateTime(value: Calendar) extends TimeValueType{
    override def toString = super.toString + "T" + prependZero(value.get(Calendar.HOUR)) + prependZero(value.get(Calendar.MINUTE)) + prependZero(value.get(Calendar.SECOND))
  }
  
  case class ICalDate(value: Calendar) extends TimeValueType
}

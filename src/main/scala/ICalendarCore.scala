package ical

object Config {
  final val DE_DE: String = "DE_DE"
  val DEFAULT_LANGUAGE: String = DE_DE
  val DEFAULT_TIMEZONE = "Europe/Berlin"
  val CRLF: String = "\r\n"
}

/**
 * Base for all Informations to transport in a Calendar
 * contentline        = name *(";" param ) ":" value CRLF
 */

trait ContentLine {
  val name: String
  val param: List[Parameter]
  val value: ParameterValue

  override def toString = {
    val sb = StringBuilder.newBuilder
    sb.append(name)
    if (!param.isEmpty) sb.append((for (c <- param; x = ";" + c) yield x) mkString)
    sb.append(":")
    sb.append(value)
    sb.append("\n")
    sb.toString
  }
}

trait Parameter {
  val name: String
  val values: List[ParameterValue]
  override def toString = {
              ""
  }
}

trait ParameterValue{
  val value: String
}


case class DefaultProperty(name: String, value: String, param: List[String]) extends ContentLine

object ICalendarCore {

  case class BeginCL(name: String = "BEGIN", value: String, param: List[String] = List()) extends ContentLine

  case class EndCL(name: String = "END", value: String, param: List[String] = List()) extends ContentLine

}

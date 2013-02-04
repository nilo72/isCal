package ical

  /**
   * Implementation of Property page 50 (4.5)
   */

trait PropertyParam{
  def name: String
  override def toString = ";" + name
}

case class AltrepParam(final val name:String="ALTREP",value:String,param:List[String]) extends PropertyParam{
  override def toString = super.toString + "=\"" + value + "\""
}
case class CNParam(final val name:String="CN",value:String,param:List[String]) extends PropertyParam{
  override def toString = super.toString + "=\"" + value + "\""
}

case class CalendarUserTypeParam(final val name:String="CUTYPE",value:String="INDIVIDUAL",param:List[String]){
  def allowedValues = List("INDIVIDUAL","GROUP","RESOURCE","ROOM","UNKNOWN")
}
case class DelegatedFromParam(final val name:String="DELEGATED-FROM",value:String="",param:List[String])
/**
 * page 24
 * Implementation of language parameter. value can only be RFC1766 defined languagedefinitionsh
 */
case class LanguageParam(final val name:String="LANGUAGE",value:String=Config.DEFAULT_LANGUAGE,param:List[String]=List()) extends Contentline


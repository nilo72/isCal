package ical


/**
 * page 24
 * Implementation of language parameter. value can only be RFC1766 defined languagedefinitionsh
 */
case class LanguageParam(final val name:String="LANGUAGE",value:String=Config.DEFAULT_LANGUAGE,param:List[String]=List()) extends Contentline
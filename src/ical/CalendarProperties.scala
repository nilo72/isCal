package ical

object CalendarProperties {
  
trait CalProp
  
/**
 * page 74 (4.7.3)
 */
case class ProdID(param:List[String],value:String="-//isCal tool//Testframe//DE",name:String="PRODID") extends Contentline with CalProp
/**
 * page 75 (4.7.4)
 */
case class Version(param:List[String],value:String="2.0",name:String="VERSION") extends Contentline with CalProp

case class Calscale(param:List[String],value:String,name:String="") extends Contentline with CalProp

case class Method(param:List[String],value:String,name:String="") extends Contentline with CalProp

}
package test

import org.scalatest.FunSpec

import ical.PropertyParameters.TZIdParam

class PropertyParamTest extends FunSpec {
 describe("The PropertyParam TZiDParam "){
   it("should have the default value EUROPE/BERLIN"){
     val tz = TZIdParam()
     assert(tz.toString === ";TZID=\"Europe/Berlin\"")
   }
 }
}
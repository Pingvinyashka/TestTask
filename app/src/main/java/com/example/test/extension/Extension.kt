package com.example.test.extension

import java.lang.Exception
import java.math.RoundingMode

fun Float.scaleTo(count: Int): String{

   /**
    * RoundingMode.CEILING for converting currencies with big difference values.
    * It could generate 0.00
    */

   val bigValue = this.toBigDecimal().setScale(count, RoundingMode.CEILING)

   return try {
      bigValue.intValueExact().toString()
   } catch (e: Exception){
      bigValue.toString()
   }

}


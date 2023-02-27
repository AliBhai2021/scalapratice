package leetcodestringprac

import scala.collection.mutable.ArrayBuffer

object Prac03_longestCommonString extends App{
  println("longestCommonPrefixString : "+longestCommonPrefixString(Array("alee","aliLee","alihan","alihkhan","ali")))

   def longestCommonPrefixString(strs:Array[String]) :String={
    println("strs.length"+ strs.length)
    println("strs.size"+ strs.size)

    var commonString = ""
    val str1 = strs(0)
    for(i<- 0 until str1.length){
      for(j <- 1 until strs.length ){
          if(str1(i)==strs(j)(i) )
            " "
          else
            return commonString
      }
      commonString += str1(i)
    }
    commonString

  }

  println("longestCommonPrefixString2 : "+longestCommonPrefixString2(Array("alee","aliLee","alihan","alihkhan","ali")))



  def longestCommonPrefixString2(strs:Array[String]) :String= ???
}
